package org.beyond.library.commons.trie;

import java.util.*;

import com.google.common.base.Objects;
import org.beyond.library.commons.utils.JsonUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class Trie {

    private static final String DEFAULT_PATH_SEPARATOR = "/";
    private static final String GLOBBING_PATH_PREFIX = "{";
    private static final String GLOBBING_PATH_POSTFIX = "}";


    private final String pathSeparator;

    private final TrieNode root;

    public Trie(final Collection<String> collection) {
        this(DEFAULT_PATH_SEPARATOR, collection);
    }

    public Trie(final String pathSeparator, final Collection<String> collection) {
        Assert.notEmpty(collection, () -> "Collection can not be empty");
        this.pathSeparator = pathSeparator;
        this.root = new TrieNode(pathSeparator);
        collection.stream()
            .filter(StringUtils::hasText)
            .forEach(this::addPath);
    }

    public void addPath(final String path) {
        Assert.hasText(path, () -> "Path can not be blank");
        if (!path.startsWith(pathSeparator)) {
            throw new IllegalArgumentException("Invalid path");
        }
        String[] split = path.split(this.pathSeparator);
        TrieNode node = this.root;
        for (int i = 1; i < split.length; i++) {
            node = node.addChildren(this.pathSeparator + split[i]);
        }
    }

    public String parsePattern(final String uri) {
        Assert.hasText(uri, () -> "Uri can not be blank");
        if (!uri.startsWith(pathSeparator)) {
            throw new IllegalArgumentException("Invalid Uri");
        }
        if (root == null || root.getChildren() == null) {
            return null;
        }
        Set<TrieNode> children = root.getChildren();
        StringBuilder builder = new StringBuilder();
        String[] split = uri.split(this.pathSeparator);
        for (int i = 1; i < split.length; i++) {
            if (CollectionUtils.isEmpty(children)) {
                return null;
            }
            final int finalI = i;
            Optional<MatchNodeResult> first = children.stream()
                .map(x -> parsePatternNode(this.pathSeparator + split[finalI], x))
                .filter(java.util.Objects::nonNull)
                .min(Comparator.comparing(MatchNodeResult::getSort));
            if (first.isEmpty()) {
                return null;
            }
            builder.append(first.get().getNode().getPath());
            children = first.get().getNode().getChildren();
        }
        return builder.toString();
    }

    private MatchNodeResult parsePatternNode(final String uri, final TrieNode node) {
        if (node.getPath().equals(uri)) {
            return new MatchNodeResult(1, node);
        }
        if (node.getPath().startsWith(this.getPathSeparator() + GLOBBING_PATH_PREFIX) && node.getPath().endsWith(GLOBBING_PATH_POSTFIX)) {
            return new MatchNodeResult(1, node);
        }
        return null;
    }


    public String getPathSeparator() {
        return pathSeparator;
    }


    public TrieNode getRoot() {
        return root;
    }


    @Override
    public String toString() {
        return JsonUtils.serialize(this);
    }

    static class TrieNode {

        private final String path;

        private Set<TrieNode> children;

        public TrieNode(final String path) {
            Assert.hasText(path, () -> "Path can not be blank");
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public Set<TrieNode> getChildren() {
            return children;
        }

        public void setChildren(final Set<TrieNode> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return JsonUtils.serialize(this);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final TrieNode trieNode = (TrieNode) o;
            return Objects.equal(path, trieNode.path);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(path);
        }

        public TrieNode addChildren(String path) {
            Assert.hasText(path, () -> "Path can not be blank");
            if (this.children == null) {
                this.children = new HashSet<>();
            }
            TrieNode childrenNode = this.getChildrenNode(path);
            if (childrenNode == null) {
                childrenNode = new TrieNode(path);
            }
            children.add(childrenNode);
            return childrenNode;
        }

        private TrieNode getChildrenNode(String path) {
            if (this.children == null) {
                return null;
            }
            return this.children
                .parallelStream()
                .filter(x -> x.path.equals(path))
                .findFirst()
                .orElse(null);
        }

    }

    static class MatchNodeResult {

        private final int sort;

        private final TrieNode node;

        MatchNodeResult(final int sort, final TrieNode node) {
            Assert.notNull(node, "Node can not be null");
            this.sort = sort;
            this.node = node;
        }

        public int getSort() {
            return sort;
        }

        public TrieNode getNode() {
            return node;
        }

    }

}
