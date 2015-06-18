In the Java programming language, an interface is a reference type, similar to a class, that can contain only constants, method signatures, default methods, static methods, and nested types. Method bodies exist only for default methods and static methods. Interfaces cannot be instantiated—they can only be implemented by classes or extended by other interfaces.

**HashSet** class implements the Set interface, backed by a hash table (actually a HashMap instance). It makes no guarantees as to the iteration order of the set; in particular, it does not guarantee that the order will remain constant over time. This class permits the null element.


### When to use *this* keyword?

It's not necessary to use *this* unless there would be ambiguities without it, i.e. when there are parameters with the same names as fields.

### Two ways of using Array.asList()

```
1. List<String> foo = new ArrayList<String>(Arrays.asList(foo2))
2. List<String> foo = Arrays.asList(foo2)
```

Second is better performance-wise BUT: it returns a List with an immutable size. Meaning you cannot add/remove elements to/from it:

Returns a fixed-size list backed by the specified array. (Changes to the returned list "write through" to the array.)

### Remove while iterating

To safely remove from a collection while iterating over it you should use an Iterator.

For example:

```
List<String> names = ....
Iterator<String> i = names.iterator();
while (i.hasNext()) {
   String s = i.next(); // must be called before you can call i.remove()
   // Do something
   i.remove();
}
```
From the Java Documentation :

The iterators returned by this class's iterator and listIterator methods are fail-fast: if the list is structurally modified at any time after the iterator is created, in any way except through the iterator's own remove or add methods, the iterator will throw a ConcurrentModificationException. Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
Perhaps what is unclear to many novices is the fact that iterating over a list using the for/foreach constructs implicitly creates an iterator which is necessarily inaccessible. This info can be found here http://docs.oracle.com/javase/1.5.0/docs/guide/language/foreach.html



