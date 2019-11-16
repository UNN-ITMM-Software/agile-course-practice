package ru.unn.agile.redblacktree;

public class RedBlackTree {
    private class Node {
        private int key, color = black;
        private Node left = nil, right = nil, parent = nil;

        Node(final int key) {
            this.key = key;
        }
    }

    private final int red = 0;
    private final int black = 1;
    private final Node nil = new Node(-1);

    private Node root = nil;
    private int size = 0;

    public RedBlackTree() { }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public boolean remove(final int value) {
        Node z = getNode(value);

        if (z == nil) {
            return false;
        }

        Node x;
        Node y = z;

        int yOriginalColor = y.color;

        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = treeMinimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);

            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == black) {
            deleteFix(x);
        }

        --size;
        return true;
    }

    public boolean find(final int expected) {
        Node found = getNode(expected);
        return found != nil;
    }

    private Node getNode(final int expected) {
        if (root == nil) {
            return nil;
        }

        Node temp = root;

        while (true) {
            if (expected == temp.key) {
                return temp;
            } else if (expected < temp.key) {
                if (temp.left == nil) {
                    return nil;
                } else {
                    temp = temp.left;
                }
            } else {
                if (temp.right == nil) {
                    return nil;
                } else {
                    temp = temp.right;
                }
            }
        }
    }

    public void insert(final int value) {
        var node = new Node(value);

        Node temp = root;

        if (root == nil) {
            root = node;
            node.color = black;
            node.parent = nil;
        } else {
            node.color = red;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
        ++size;
    }

    private void fixTree(final Node start) {
        Node node = start;
        while (node.parent.color == red) {
            Node uncle;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != nil && uncle.color == red) {
                    node = updateNodes(node, uncle);
                    continue;
                }
                if (node == node.parent.right) {
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.color = black;
                node.parent.parent.color = red;
                rotateRight(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;

                if (uncle != nil && uncle.color == red) {
                    node = updateNodes(node, uncle);
                    continue;
                }
                if (node == node.parent.left) {
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = black;
                node.parent.parent.color = red;
                rotateLeft(node.parent.parent);
            }
        }
        root.color = black;
    }

    private Node updateNodes(final Node node, final Node uncle) {
        node.parent.color = black;
        uncle.color = black;
        node.parent.parent.color = red;
        return node.parent.parent;
    }

    private void rotateLeft(final Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    private void rotateRight(final Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    private void transplant(final Node target, final Node with) {
        if (target.parent == nil) {
            root = with;
        } else if (target == target.parent.left) {
            target.parent.left = with;
        } else {
            target.parent.right = with;
        }
        with.parent = target.parent;
    }

    private void deleteFix(final Node start) {
        Node x = start;
        while (x != root && x.color == black) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.color == red) {
                    w.color = black;
                    x.parent.color = red;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == black && w.right.color == black) {
                    w.color = red;
                    x = x.parent;
                    continue;
                } else if (w.right.color == black) {
                    w.left.color = black;
                    w.color = red;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if (w.right.color == red) {
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.right.color = black;
                    rotateLeft(x.parent);
                    x = root;
                }
            } else {
                Node w = x.parent.left;
                if (w.color == red) {
                    w.color = black;
                    x.parent.color = red;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == black && w.left.color == black) {
                    w.color = red;
                    x = x.parent;
                    continue;
                } else if (w.left.color == black) {
                    w.right.color = black;
                    w.color = red;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if (w.left.color == red) {
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.left.color = black;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = black;
    }

    private Node treeMinimum(final Node subTreeRoot) {
        Node temp = subTreeRoot;

        while (temp.left != nil) {
            temp = temp.left;
        }
        return temp;
    }
}
