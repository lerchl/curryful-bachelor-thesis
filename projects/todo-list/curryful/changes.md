# Changes
Changes I had to apply to ChatGTP's code for it to compile.

## Use Java 21's preview feature of unnamed classes
The data ChatGPT was trained with is too old to know about Java 21's unnamed classes.
I have therefore taken it into my responsibility to refactor the code to use this feature.
The framework uses Java 21 and one of it's motivators was this preview feature, hence I
think it is only fair to take advantage of it.

## Create Maybe from Stream#findFirst()'s Optional
It generated this code:

```java
private static Maybe<Todo> findTodoById(Maybe<Integer> id) {
    return id.flatMap(i -> todos.stream().filter(todo -> todo.getId() == i).findFirst());
}
```

Ending up with the error `Type mismatch: cannot convert from Optional<Todo> to Maybe<Todo>Java(16777235)`.

I had to wrap the stream's result in a `Maybe#from(Optional)`.

## Remove hallucination of Try#ifFailure(Consumer\<Exception\>)
It tried this:

```java
.ifFailure(e -> {
```

Appended to the last apply call of Sever#listen. Ending up with the error
`The method ifFailure((<no type> e) -> {}) is undefined for the type Try<capture#1-of ?>Java(67108964)`.

I removed the method call and stored the returned Try in a variable. And then checked whether the Try
is a failure to then run ChatGPT's code.
