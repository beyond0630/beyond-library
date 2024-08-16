package org.beyond.library.commons.trie;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.beyond.library.commons.utils.JsonUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class PathTrie {

    private static final String DEFAULT_PATH_SEPARATOR = "/";
    private static final String GLOBBING_PATH_PREFIX = "{";
    private static final String GLOBBING_PATH_POSTFIX = "}";

    private final String pathSeparator;
    private final TrieNode root;

    public PathTrie(Collection<String> paths) {
        this(DEFAULT_PATH_SEPARATOR, paths);
    }

    public PathTrie(String pathSeparator, Collection<String> paths) {
        Assert.notEmpty(paths, "Collection cannot be empty");
        this.pathSeparator = pathSeparator;
        this.root = new TrieNode(pathSeparator);
        paths.stream()
            .filter(StringUtils::hasText)
            .forEach(this::addPath);
    }

    public void addPath(String path) {
        Assert.hasText(path, "Path cannot be blank");
        if (!path.startsWith(pathSeparator)) {
            throw new IllegalArgumentException("Invalid path");
        }

        TrieNode node = this.root;
        String[] segments = path.split(this.pathSeparator);
        for (String segment : segments) {
            if (segment.isEmpty()) {
                // Skip empty segments
                continue;
            }
            node = node.addChildren(pathSeparator + segment);
        }
    }

    public void removePath(String path) {
        Assert.hasText(path, "Path cannot be blank");
        if (!path.startsWith(pathSeparator)) {
            throw new IllegalArgumentException("Invalid path");
        }

        String[] segments = path.split(this.pathSeparator);
        TrieNode node = this.root;
        Stack<TrieNode> nodeStack = new Stack<>();

        for (int i = 1; i < segments.length; i++) {
            node = node.getChildrenNode(pathSeparator + segments[i]);
            if (node == null) {
                return;
            }
            nodeStack.push(node);
        }

        // Remove nodes from leaf to root
        while (!nodeStack.isEmpty()) {
            TrieNode currentNode = nodeStack.pop();
            currentNode.decrementReferenceCount();
            if (currentNode.getReferenceCount() == 0) {
                TrieNode parentNode = nodeStack.isEmpty() ? root : nodeStack.peek();
                parentNode.removeChildren(currentNode.getPath());
            }
        }
    }

    public String parsePattern(String uri) {
        Assert.hasText(uri, "Uri cannot be blank");
        if (!uri.startsWith(pathSeparator)) {
            throw new IllegalArgumentException("Invalid Uri");
        }

        TrieNode node = this.root;
        String[] segments = uri.split(this.pathSeparator);
        StringBuilder matchedPattern = new StringBuilder();

        for (int i = 1; i < segments.length; i++) {
            String segment = pathSeparator + segments[i];
            TrieNode matchingNode = node.match(segment);

            if (matchingNode == null) {
                return null;
            }
            matchedPattern.append(matchingNode.getPath());
            node = matchingNode;
        }

        return matchedPattern.toString();
    }

    static class TrieNode {
        private final String path;
        private final AtomicInteger referenceCount;
        private final Map<String, TrieNode> children;

        public TrieNode(String path) {
            Assert.hasText(path, "Path cannot be blank");
            this.path = path;
            this.referenceCount = new AtomicInteger(1);
            this.children = new ConcurrentHashMap<>();
        }

        public String getPath() {
            return path;
        }

        public int getReferenceCount() {
            return referenceCount.get();
        }

        public TrieNode addChildren(String path) {
            return children.computeIfAbsent(path, TrieNode::new);
        }

        public void removeChildren(String path) {
            TrieNode childNode = children.get(path);
            if (childNode != null && childNode.referenceCount.get() == 0) {
                children.remove(path);
            }
        }

        public TrieNode getChildrenNode(String path) {
            return children.get(path);
        }

        public TrieNode match(String uriSegment) {
            TrieNode exactMatch = children.get(uriSegment);
            if (exactMatch != null) {
                return exactMatch;
            }

            // Handle dynamic segment match
            return children.values().stream()
                .filter(node -> node.getPath().startsWith(GLOBBING_PATH_PREFIX) && node.getPath().endsWith(GLOBBING_PATH_POSTFIX))
                .findFirst()
                .orElse(null);
        }

        public void decrementReferenceCount() {
            referenceCount.decrementAndGet();
        }
    }
}
