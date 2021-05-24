**TECH11 INTERVIEW PROJECT GUIDE**

This project uses java8 and maven3.6. To start uo, use the command: `mvn clean thorntail:run`

The project start off on port `8080` with context path `\api`.

A `UserResource` is the only resource available in this project, this resource is accessible on `\api\users`. 

The following endpoints are available in this project:

> 1. `GET` `\api\users\test`: Returns a String `"Test Endpoint"`, this does nothing, just helps to tell that the server is up and ready for requests.

> 2. `GET` `\api\users`: Returns all users as a list of users in the DB with response status 200, this could be empty anyways.

> 3. `GET` `\api\users\{id}`: Here `{id}` is a numeric value from 1 on wards, this endpoint returns a single user, with status 200 if it exists in the database, or throws an exception

> 4. `POST` `\api\users`: This is a create endpoint that takes a `JSON` body which will be deserialized to a `User` object. It saves this object and returns the output(saved) `User` with a response status `201` if successful or throws an exception otherwise.

> 5. `PUT` `\api\users\{id}`: This is an update endpoint that takes `{id}` as parameter and a `JSON` body which deserializes to a `User` object that will be used to update the user with the given id, returns with status 200, along with the updated user, or throws an exception otherwise.

> 6. `POST` `\api\users\authenticate`: This takes a `LoginDTO`, fetches a user by `email` _(a field in the `LoginDTO`)_, and validates that the `password` _(another field from the `LoginDTO`)_ matches the hashed password of the user fetched. This is an extra endpoint just to show that hashed values of a user password can be compared successfully.

* > **NB: _For simplicity, all exceptions are returned as Response with status `BAD_REQUEST (404)`_**
  
_Attached to this repository is a yaml file `(tech11TestEndpoints.yaml)` containing all requests tested on insomnia rest client, you can import this file and test with the already defined use cases._