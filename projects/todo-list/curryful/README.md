# Todo List Curryful
Original prompt (after sending curryful details):

    I have provide you with:
    - The curryful-commons library, which curryful-rest builds upon
    - The curryful-rest library, which is a simple rest framework,
      leaving out the utility classes Http and Uri, you don't need
      to know about those
    - An example application using curryful-rest

    The framework utilizes the concepts of functional programming,
    so use functional programming principles you know about as wel as
    the principles already applied in the example code.

    I want you to write a simple rest api for a todo list.
    The todo list should be stored in memory and should be accessible
    through the following endpoints:

    - GET /todos
    - POST /todos
    - GET /todos/:id
    - PUT /todos/:id
    - DELETE /todos/:id
    - POST /todos/:id/toggle

    A todo is described by the following json object:
    {
      "id": 1,
      "title": "Buy milk",
      "completed": false
    }

    As you can see, the framework does not handle json on its own. Please
    use jackson to parse json to objects and objects to json.

## Run
Can be run with this command: `java --enable-preview --source 21 -classpath lib/commons-0.0.1.jar:lib/rest-0.0.1.jar:lib/jackson-core-2.17.0.jar:lib/jackson-annotations-2.17.0.jar:lib/jackson-databind-2.17.0.jar src/Api.java`

## Changes
Changes I applied to ChatGTP's code

### Use Java 21's preview feature of unnamed classes
The data ChatGPT was trained with is too old to know about Java 21's unnamed classes.
I have therefore taken it into my responsibility to refactor the code to use this feature.
The framework uses Java 21 and one of it's motivators was this preview feature, hence I
think it is only fair to take advantage of it.

### Create Maybe from Stream#findFirst()'s Optional
It generated this code:

```java
private static Maybe<Todo> findTodoById(Maybe<Integer> id) {
    return id.flatMap(i -> todos.stream().filter(todo -> todo.getId() == i).findFirst());
}
```

Ending up with the error `Type mismatch: cannot convert from Optional<Todo> to Maybe<Todo>Java(16777235)`.

I had to wrap the stream's result in a `Maybe#from(Optional)`.

### Remove hallucination of Try#ifFailure(Consumer\<Exception\>)
It tried this:

```java
.ifFailure(e -> {
```

Appended to the last apply call of Sever#listen. Ending up with the error
`The method ifFailure((<no type> e) -> {}) is undefined for the type Try<capture#1-of ?>Java(67108964)`.

I removed the method call and stored the returned Try in a variable. And then checked whether the Try
is a failure to then run ChatGPT's code.

### Make (now) inner Todo class static
Because of the error `Cannot construct instance of `Api$Todo`: non-static inner classes like this can only by instantiated using default, no-argument constructor`
