# Requests
Library for making HTTP calls with JSON parameters for Java.
With this library, you can make GET, POST, PUT, PATCH, and DELETE requests to an API, sending data in JSON format and getting responses in JSON format.

## Available Methods

`Requests` offers the following methods for making HTTP calls:

### GET Method:

```java
String url = "https://api.example.com/data";
String response = httpClient.doGet(url);
System.out.println("GET Response: " + response);
```

### POST Method:

```java
String url = "https://api.example.com/create";
YourObject requestBody = new YourObject();
// Set properties of YourObject as needed
String response = httpClient.doPost(url, requestBody);
System.out.println("POST Response: " + response);
```

### PUT Method:

```java
String url = "https://api.example.com/update/123";
YourObject requestBody = new YourObject();
// Set properties of YourObject as needed
String response = httpClient.doPut(url, requestBody);
System.out.println("PUT Response: " + response);
```

### PATCH Method:

```java
String url = "https://api.example.com/update/123";
YourObject requestBody = new YourObject();
// Set properties of YourObject as needed
String response = httpClient.doPatch(url, requestBody);
System.out.println("PATCH Response: " + response);
```

### DELETE Method:

```java
String url = "https://api.example.com/delete/123";
String response = httpClient.doDelete(url);
System.out.println("DELETE Response: " + response);
```

## Considerations

- Before making requests, ensure you have the correct API URL and JSON parameters needed for each type of request (GET, POST, PUT, PATCH, DELETE).
- Handle exceptions and errors properly that may occur during HTTP calls to ensure a stable application flow.

That's it! Now you can use `Requests` to interact with your API and make HTTP calls with JSON parameters easily and efficiently.
