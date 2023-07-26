# Requests
Library for making HTTP calls with JSON parameters for Java.
With this library, you can make GET, POST, PUT, PATCH, and DELETE requests to an API, sending data in JSON format and getting responses in JSON format.

## Making HTTP Requests

### GET Method:

```java
String url = "https://api.example.com/data";
Map<String, String> headers = new HashMap<>();
headers.put("Authorization", "Bearer your_access_token");
headers.put("Custom-Header", "Custom-Value");
// Headers can be null
String response = httpClient.doGet(url, headers);
System.out.println("GET Response: " + response);
```

### POST Method:

```java
String url = "https://api.example.com/create";
Object requestBody = new YourObject();
// Set properties of YourObject as needed
Map<String, String> headers = new HashMap<>();
headers.put("Authorization", "Bearer your_access_token");
// Headers can be null
String response = httpClient.doPost(url, requestBody, headers);
System.out.println("POST Response: " + response);
```

### PUT Method:

```java
String url = "https://api.example.com/update/123";
Object requestBody = new YourObject();
// Set properties of YourObject as needed
Map<String, String> headers = new HashMap<>();
headers.put("Authorization", "Bearer your_access_token");
// Headers can be null
String response = httpClient.doPut(url, requestBody, headers);
System.out.println("PUT Response: " + response);
```

### PATCH Method:

```java
String url = "https://api.example.com/update/123";
Object requestBody = new YourObject();
// Set properties of YourObject as needed
Map<String, String> headers = new HashMap<>();
headers.put("Authorization", "Bearer your_access_token");
// Headers can be null
String response = httpClient.doPatch(url, requestBody, headers);
System.out.println("PATCH Response: " + response);
```

### DELETE Method:

```java
String url = "https://api.example.com/delete/123";
Map<String, String> headers = new HashMap<>();
headers.put("Authorization", "Bearer your_access_token");
// Headers can be null
String response = httpClient.doDelete(url, headers);
System.out.println("DELETE Response: " + response);
```

## Considerations

- Before making requests, ensure you have the correct API URL and JSON parameters needed for each type of request (GET, POST, PUT, PATCH, DELETE).
- Handle exceptions and errors properly that may occur during HTTP calls to ensure a stable application flow.

That's it! Now you can use `Requests` to interact with your API and make HTTP calls with JSON parameters easily and efficiently.
