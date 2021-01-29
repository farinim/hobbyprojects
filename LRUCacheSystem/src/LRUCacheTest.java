import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LRUCacheTest {

    LRUCache cache;
    @Before
    public void setUp() throws Exception {
        cache = new LRUCache(5);
    }

    @After
    public void tearDown() throws Exception {
        cache=null;
    }

    @Test
    public void size() {
    }

    @Test
    public void add1() {
        cache.add("a","aa");
        assertEquals(cache.size(),1);
    }
    @Test
    public void add2() {
        LRUCache lrucache = new LRUCache(2);
        lrucache.add("a","aa");
        lrucache.add("b","bb");
        lrucache.add("c","cc");
        assertEquals(2,lrucache.size());
        String aa = (String) lrucache.get("a");
        assertEquals(null,aa);
        assertNotEquals("aa",aa);
        String bb = (String) lrucache.get("b");
        assertEquals("bb",bb);
        String cc = (String) lrucache.get("c");
        assertEquals("cc",cc);
    }

    @Test
    public void evict() {
        cache.add("a","aa");
        int before = cache.size();
        cache.evict();
        assertTrue(cache.size() < before);
    }

    @Test
    public void add() {
        cache.add("a","aa");
        cache.add("b","bb");
        cache.add("c","cc");
        cache.add("d","dd");
        cache.add("e","ee");
        assertEquals("e",cache.head);
        assertEquals("a",cache.tail);
        cache.add("f","ff");
        assertEquals(5,cache.size());
        assertEquals("f",cache.head);
        assertEquals("b",cache.tail);
        assertEquals(5,cache.size());
    }
    @Test
    public void hit0() {
        cache.add("a","aa");
        cache.add("b","bb");
        cache.add("c","cc");
        cache.add("d","dd");
        cache.add("e","ee");
        assertEquals("e",cache.head);
        assertEquals("a",cache.tail);
        cache.hit("f");
        assertEquals("e",cache.head);
        assertEquals("a",cache.tail);
    }
    @Test
    public void hit1() {
        cache.add("a","aa");
        cache.add("b","bb");
        cache.add("c","cc");
        cache.add("d","dd");
        cache.add("e","ee");
        assertEquals("e",cache.head);
        assertEquals("a",cache.tail);
        cache.hit("a");
        assertEquals("a",cache.head);
        assertEquals("b",cache.tail);
        cache.hit("a");
        assertEquals("a",cache.head);
        assertEquals("b",cache.tail);
    }

    @Test
    public void get1() {
        cache.add("a","aa");
        cache.add("b","bb");
        cache.add("c","cc");
        cache.add("d","dd");
        cache.add("e","ee");
        Object ee = cache.get("e");
        assertEquals("ee",ee);
    }

    @Test
    public void get2() {
        cache.add("a","aa");
        cache.add("b","bb");
        cache.add("c","cc");
        cache.add("d","dd");
        cache.add("e","ee");
        cache.add("f","ff");
        Object none = cache.get("a");
        assertNull(none);
    }

    @Test
    public void get0() {
        cache.add("a","aa");
        cache.add("b","bb");
        cache.add("c","cc");
        cache.add("d","dd");
        cache.add("e","ee");
        Object none = cache.get("f");
        assertNull(none);
        Object ee = cache.get("e");
        assertEquals("ee",ee);
    }
}