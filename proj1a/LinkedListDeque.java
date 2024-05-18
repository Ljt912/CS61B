public class LinkedListDeque<T> {
    private Node _sentinel;
    private int _size;

    public LinkedListDeque() {
        _size = 0;
        _sentinel = new Node(null);
        _sentinel._nextNode = _sentinel;
        _sentinel._preNode = _sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        if (other != null) {
            _size = 0;
            _sentinel = new Node(null);
            _sentinel._preNode = _sentinel;
            _sentinel._nextNode = _sentinel;
            Node itr = other._sentinel;
            while (_size < other.size()) {
                itr = itr._nextNode;
                addLast(itr._item);
            }
        }
    }

    public T getRecursive(int index) {
        if (index > size()) {
            return null;
        }
        return _sentinel.getRecursiveHelper(index + 1);
    }

    public void addFirst(T item) {
        _size += 1;
        Node newNode = new Node(_sentinel, item, _sentinel._nextNode);
        _sentinel._nextNode._preNode = newNode;
        _sentinel._nextNode = newNode;
    }

    public void addLast(T item) {
        _size += 1;
        Node newNode = new Node(_sentinel._preNode, item, _sentinel);
        _sentinel._preNode._nextNode = newNode;
        _sentinel._preNode = newNode;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        _size -= 1;
        T itemRemoved = _sentinel._nextNode._item;
        _sentinel._nextNode._nextNode._preNode = _sentinel;
        _sentinel._nextNode = _sentinel._nextNode._nextNode;
        return itemRemoved;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        _size -= 1;
        T itemRemoved = _sentinel._preNode._item;
        _sentinel._preNode._preNode._nextNode = _sentinel;
        _sentinel._preNode = _sentinel._preNode._preNode;
        return itemRemoved;
    }

    public T get(int index) {
        if (index >= size() + 1) {
            return null;
        }
        Node itr = _sentinel;
        while (index > 0) {
            index -= 1;
            itr = itr._nextNode;
        }
        return itr._item;
    }

    public int size() {
        return _size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        int count = 0;
        Node itr = _sentinel;
        while (count < size()) {
            count += 1;
            itr = itr._nextNode;
            System.out.print(itr._item);
            System.out.print(' ');
        }
        System.out.println();
    }

    private class Node {
        private Node _preNode;
        private T _item;
        private Node _nextNode;

        private Node() {
            _preNode = null;
            _item = null;
            _nextNode = null;
        }

        private T getRecursiveHelper(int index) {
            if (index == 0) {
                return _item;
            } else {
                return  _nextNode.getRecursiveHelper(index - 1);
            }
        }

        private Node(Node preNode, T item, Node nextNode) {
            _preNode = preNode;
            _item = item;
            _nextNode = nextNode;
        }

        private Node(T item) {
            _preNode = null;
            _item = item;
            _nextNode = null;
        }
    }
}
