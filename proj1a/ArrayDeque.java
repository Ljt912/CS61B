public class ArrayDeque<T> {
    private T[] _items;
    private  int _maxSize = 1;
    private int _size;
    public ArrayDeque() {
        _items = (T[]) new Object[_maxSize];
        _size = 0;
    }

//    public ArrayDeque(ArrayDeque other) {
//        _items = (T[]) new Object[other._maxSize];
//        _size = other._size;
//        for (int i = 0; i < other._size; i++) {
//            _items[i] = (T) other._items[i];
//        }
//    }

    private void addResize() {
        if (_maxSize >= 16) {
            _maxSize *= 2;
            T[] newItems = (T[]) new Object[_maxSize];
            System.arraycopy(_items, 0, newItems, 0, _size);
            _items = newItems;
        } else {
            _maxSize += 1;
            T[] newItems = (T[]) new Object[_maxSize];
            System.arraycopy(_items, 0, newItems, 0, _size);
            _items = newItems;
        }
    }

    public void addFirst(T item) {
        if (_size == _maxSize) {
            addResize();
        }
        int itr = _size;
        while (itr > 0) {
            _items[itr] = _items[itr - 1];
            itr -= 1;
        }
        _items[itr] = item;
        _size = _size + 1;
    }


    public void addLast(T item) {
        if (_size == _maxSize) {
            addResize();
        }
        _items[_size++] = item;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public int size() {
        return _size;
    }

    public void printDeque() {
        for (int i = 0; i < _size; i++) {
            System.out.print(_items[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    private void removeResize() {
        if (_maxSize >= 16) {
            if (_size / _maxSize < 0.25) {
                _maxSize /= 2;
                T[] newItems = (T[]) new Object[_maxSize];
                System.arraycopy(_items, 0, newItems, 0, _size);
                _items = newItems;
            }
        } else {
            if (_size != _maxSize) {
                if (_size > 1) {
                    _maxSize -= 1;
                }
                T[] newItems = (T[]) new Object[_maxSize];
                System.arraycopy(_items, 0, newItems, 0, _size);
                _items = newItems;
            }
        }
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = _items[0];
            for (int i = _size - 1; i > 0; i--) {
                _items[i - 1] = _items[i];
            }
            _size -= 1;
            removeResize();
            return temp;
        }

    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = _items[--_size];
            removeResize();
            return temp;
        }
    }

    public T get(int index) {
        if (index >= _size) {
            return null;
        } else {
            return _items[index];
        }
    }
}
