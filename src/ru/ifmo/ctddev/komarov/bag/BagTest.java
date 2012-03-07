package ru.ifmo.ctddev.komarov.bag;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ifmo.ctddev.komarov.bag.Bag;

import java.util.*;

import static org.junit.Assert.*;

public class BagTest {

    Collection<Integer> bag;

    @Before
    public void setUp() throws Exception {
        bag = new TreeBag<Integer>();
    }

    public <T extends Comparable<T>> Collection<T> newInstance() {
        return new TreeBag<T>();
    }

    public <T extends Comparable<T>> Collection<T> newInstance(Collection<? extends T> collection) {
        return new TreeBag<T>(collection);
    }

    @Test
    public void test1() throws Exception {
        bag.add(2);
        bag.add(3);
        bag.add(4);
        assertTrue(bag.contains(2));
        assertTrue(bag.contains(3));
        assertTrue(bag.contains(4));
        assertFalse(bag.contains(5));
    }

    @Test
    public void testClear() throws Exception {
        bag.add(2);
        bag.add(3);
        bag.add(4);
        assertTrue(bag.contains(2));
        assertTrue(bag.contains(3));
        assertTrue(bag.contains(4));
        assertEquals(bag.size(), 3);
        assertFalse(bag.isEmpty());
        bag.clear();
        assertFalse(bag.contains(2));
        assertFalse(bag.contains(3));
        assertFalse(bag.contains(4));
        assertTrue(bag.isEmpty());
        assertFalse(bag.iterator().hasNext());
        assertEquals(bag.size(), 0);
    }

    @Test
    public void testClearByIterator() throws Exception {
        bag.add(2);
        bag.add(3);
        bag.add(4);
        assertTrue(bag.contains(2));
        assertTrue(bag.contains(3));
        assertTrue(bag.contains(4));
        assertEquals(bag.size(), 3);
        assertFalse(bag.isEmpty());
        Iterator<Integer> it = bag.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        assertFalse(bag.contains(2));
        assertFalse(bag.contains(3));
        assertFalse(bag.contains(4));
        assertTrue(bag.isEmpty());
        assertFalse(bag.iterator().hasNext());
        assertEquals(bag.size(), 0);

    }

    @Test
    public void remove() throws Exception {
        bag.add(2);
        bag.add(2);
        bag.add(2);
        assertTrue(bag.contains(2));
        bag.remove(2);
        assertTrue(bag.contains(2));
        bag.remove(2);
        assertTrue(bag.contains(2));
        bag.remove(2);
        assertFalse(bag.contains(2));
    }

    @Test
    public void iterate() throws Exception {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                bag.add(j);
                assertEquals(i * 100 + j + 1, bag.size());
            }
        }
        final int[] count = new int[100];
        int i = 100, bufferNumber = -1;
        for (final Integer number : bag) {
            count[number]++;
            if (number != bufferNumber) {
                assertEquals(100, i);
                i = 0;
                bufferNumber = number;
            }
            i++;
        }
        final int[] hundred = new int[100];
        Arrays.fill(hundred, 100);
        assertArrayEquals(hundred, count);
    }

    private class Point implements Comparable<Point> {
        int x, y;

        private Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Point point = (Point) o;

            if (x != point.x) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return x;
        }

        
        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public int compareTo(Point o) {
            return x - o.x;
        }
    }

    @Test
    public void complicatedType() {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));

        final Collection<Point> bagPoint = newInstance();
        bagPoint.addAll(points);

        final int[] a = new int[3];
        final Iterator<Point> iterator = bagPoint.iterator();
        while (iterator.hasNext()) {
            assertTrue(bagPoint.contains(new Point(1, 42)));
            Point point = iterator.next();
            int count = 0;
            for (Point p : points) {
                if (p == point) {
                    count++;
                }
            }
            assertEquals(count, 1);
            a[point.y]++;
            iterator.remove();
        }
        assertFalse(bagPoint.contains(new Point(1, 42)));
        Assert.assertArrayEquals(new int[]{1, 1, 1}, a);
    }

    @Test
    public void complicatedTypeIteratorRemove1() {
        final Collection<Point> bagPoint = newInstance();
        bagPoint.add(new Point(1, 0));
        bagPoint.add(new Point(1, 1));
        bagPoint.add(new Point(1, 2));

        final Iterator<Point> iterator = bagPoint.iterator();
        Point removed = iterator.next();
        iterator.remove();
        for (Point p : bagPoint) {
            assertNotSame(p, removed);
        }
    }

    @Test
    public void complicatedTypeIteratorRemove2() {
        final Collection<Point> bagPoint = newInstance();
        bagPoint.add(new Point(1, 0));
        bagPoint.add(new Point(1, 1));
        bagPoint.add(new Point(1, 2));

        final Iterator<Point> iterator = bagPoint.iterator();
        iterator.next();
        Point removed = iterator.next();
        iterator.remove();
        for (Point p : bagPoint) {
            assertNotSame(p, removed);
        }
    }

    @Test
    public void complicatedTypeIteratorRemove3() {
        final Collection<Point> bagPoint = newInstance();
        bagPoint.add(new Point(1, 0));
        bagPoint.add(new Point(1, 1));
        bagPoint.add(new Point(1, 2));

        final Iterator<Point> iterator = bagPoint.iterator();
        iterator.next();
        iterator.next();
        Point removed = iterator.next();
        iterator.remove();
        for (Point p : bagPoint) {
            assertNotSame(p, removed);
        }
    }

    @Test
    public void collectionConstructor() {
        final Collection<Integer> bag1 = newInstance(Arrays.asList(2, 3, 4));
        assertTrue(bag1.contains(2));
        assertTrue(bag1.contains(3));
        assertTrue(bag1.contains(4));
        assertFalse(bag1.contains(5));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationException() throws Exception {
        bag.add(1);
        bag.add(2);
        bag.add(3);
        for (final Integer next : bag) {
            bag.remove(1);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationExceptionWithTwoIterators() throws Exception {
        bag.add(1);
        bag.add(2);
        Iterator<Integer> it1 = bag.iterator();
        Iterator<Integer> it2 = bag.iterator();
        it2.next();
        it2.remove();
        it1.next();
    }

    @Test
    public void testTwoIterators() {
        bag.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Iterator<Integer> it1 = bag.iterator();
        Iterator<Integer> it2 = bag.iterator();
        for (int i = 0; i < 8; i++) {
            int x = it1.next();
            int y = it2.next();
            assertEquals(x, y);
        }
        assertFalse(it1.hasNext());
        assertFalse(it2.hasNext());
    }

    @Test
    public void testBadRemove() throws Exception {
        assertFalse(bag.remove(0));
    }

    @Test
    public void testRemoveSize() throws Exception {
        bag.add(2);
        bag.add(2);
        assertEquals(2, bag.size());
        bag.remove(2);
        assertEquals(1, bag.size());
        bag.remove(2);
        assertEquals(0, bag.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextException() throws Exception {
        bag.add(1);
        final Iterator<Integer> iterator = bag.iterator();
        iterator.next();
        assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test
    public void emptyIterator() throws Exception {
        Iterator<Integer> it = bag.iterator();
        assertFalse(it.hasNext());
        assertFalse(it.hasNext());
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyIteratorNoSuchElementException() throws Exception {
        Iterator<Integer> it = bag.iterator();
        assertFalse(it.hasNext());
        assertFalse(it.hasNext());
        assertFalse(it.hasNext());
        it.next();
    }

}