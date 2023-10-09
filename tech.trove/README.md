# Challenge :  tech-trove-ecommerce

This microservice covers the basic operations of a microservice, the business to be covered is for an ecommerce.

# Order Controller API Documentation

## Table of Contents
- [Create a New Order](#create-a-new-order)
- [Get Order by ID](#get-order-by-id)
- [Update Order by ID](#update-order-by-id)
- [Delete Order by ID](#delete-order-by-id)
- [Dependencies](#dependencies)

---

### Create a New Order
- **Endpoint:** `/order`
- **HTTP Method:** `POST`
- **Description:** Create a new order.
- **Request Payload:**
    - **Type:** `OrderRequestDto`
    - **Description:** DTO containing order details required for creation.
- **Response:**
    - **Type:** `OrderChannelDto`
    - **Description:** DTO representing the created order.
- **HTTP Status:** `201` (Order successfully created.)

### Get Order by ID
- **Endpoint:** `/order/{orderId}`
- **HTTP Method:** `GET`
- **Description:** Retrieve an order by its ID.
- **Path Variable:**
    - **Name:** `orderId`
    - **Type:** `String`
    - **Description:** ID of the order to retrieve.
- **Response:**
    - **Type:** `OrderChannelDto`
    - **Description:** DTO representing the retrieved order.
- **HTTP Status:** `200` (Order successfully retrieved.)

### Update Order by ID
- **Endpoint:** `/order/{orderId}`
- **HTTP Method:** `PUT`
- **Description:** Update an existing order by its ID.
- **Path Variable:**
    - **Name:** `orderId`
    - **Type:** `String`
    - **Description:** ID of the order to update.
- **Request Payload:**
    - **Type:** `OrderUpdateRequestDto`
    - **Description:** DTO containing order details required for updating.
- **Response:**
    - **Type:** `OrderChannelDto`
    - **Description:** DTO representing the updated order.
- **HTTP Status:** `200` (Order successfully updated.)

### Delete Order by ID
- **Endpoint:** `/order/{orderId}`
- **HTTP Method:** `DELETE`
- **Description:** Delete an order by its ID.
- **Path Variable:**
    - **Name:** `orderId`
    - **Type:** `String`
    - **Description:** ID of the order to delete.
- **HTTP Status:** `204` (Order successfully deleted.)

### Dependencies:
- **Service:** `OrderService`

# Category Controller API Documentation

## Table of Contents
- [Create a New Category](#create-a-new-category)
- [Get Category by ID](#get-category-by-id)
- [Dependencies](#dependencies)

---

### Create a New Category
- **Endpoint:** `/product/category`
- **HTTP Method:** `POST`
- **Description:** Create a new category.
- **Request Payload:**
    - **Type:** `CategoryRequestDto`
    - **Description:** DTO containing category details required for creation.
- **Response:**
    - **Type:** `CategoryChannelDto`
    - **Description:** DTO representing the created category.
- **HTTP Status:** `201` (Category created successfully.)

### Get Category by ID
- **Endpoint:** `/product/category/{categoryId}`
- **HTTP Method:** `GET`
- **Description:** Retrieve a category by its ID.
- **Path Variable:**
    - **Name:** `categoryId`
    - **Type:** `String`
    - **Description:** ID of the category to retrieve.
- **Response:**
    - **Type:** `CategoryChannelDto`
    - **Description:** DTO representing the retrieved category.
- **HTTP Status:** `200` (Category successfully retrieved.)

### Dependencies:
- **Service:** `CategoryService`

# Inventory Controller API Documentation

## Table of Contents
- [Create a New Inventory](#create-a-new-inventory)
- [Get Inventory by Product ID](#get-inventory-by-product-id)
- [Update Inventory by Product ID](#update-inventory-by-product-id)
- [Delete Inventory by Product ID](#delete-inventory-by-product-id)
- [Dependencies](#dependencies)

---

### Create a New Inventory
- **Endpoint:** `/product/inventory`
- **HTTP Method:** `POST`
- **Description:** Create a new inventory record.
- **Request Payload:**
    - **Type:** `InventoryRequestDto`
    - **Description:** DTO containing inventory details required for creation.
- **Response:**
    - **Type:** `InventoryChannelDto`
    - **Description:** DTO representing the created inventory record.
- **HTTP Status:** `201` (Inventory created successfully.)

### Get Inventory by Product ID
- **Endpoint:** `/product/inventory/{productId}`
- **HTTP Method:** `GET`
- **Description:** Retrieve inventory record by its associated product ID.
- **Path Variable:**
    - **Name:** `productId`
    - **Type:** `String`
    - **Description:** ID of the product whose inventory record is to be retrieved.
- **Response:**
    - **Type:** `InventoryChannelDto`
    - **Description:** DTO representing the retrieved inventory record.
- **HTTP Status:** `201` (Inventory successfully retrieved.)

### Update Inventory by Product ID
- **Endpoint:** `/product/inventory/{productId}`
- **HTTP Method:** `PUT`
- **Description:** Update inventory record by its associated product ID.
- **Path Variable:**
    - **Name:** `productId`
    - **Type:** `String`
    - **Description:** ID of the product whose inventory record is to be updated.
- **Request Payload:**
    - **Type:** `InventoryUpdateRequestDto`
    - **Description:** DTO containing updated inventory details.
- **Response:**
    - **Type:** `InventoryChannelDto`
    - **Description:** DTO representing the updated inventory record.
- **HTTP Status:** `200` (Inventory updated successfully.)

### Delete Inventory by Product ID
- **Endpoint:** `/product/inventory/{productId}`
- **HTTP Method:** `DELETE`
- **Description:** Delete inventory record by its associated product ID.
- **Path Variable:**
    - **Name:** `productId`
    - **Type:** `String`
    - **Description:** ID of the product whose inventory record is to be deleted.
- **HTTP Status:** `204` (Inventory successfully deleted.)

### Dependencies:
- **Service:** `InventoryService`

# Product Controller API Documentation

## Table of Contents
- [Create a New Product](#create-a-new-product)
- [Get Product by Product ID](#get-product-by-product-id)
- [Update Product by Product ID](#update-product-by-product-id)
- [Delete Product by Product ID](#delete-product-by-product-id)
- [Dependencies](#dependencies)

---

### Create a New Product
- **Endpoint:** `/product`
- **HTTP Method:** `POST`
- **Description:** Create a new product record.
- **Request Payload:**
    - **Type:** `ProductRequestDto`
    - **Description:** DTO containing product details required for creation.
- **Response:**
    - **Type:** `ProductChannelDto`
    - **Description:** DTO representing the created product record.
- **HTTP Status:** `201` (Product created successfully.)

### Get Product by Product ID
- **Endpoint:** `/product/{productId}`
- **HTTP Method:** `GET`
- **Description:** Retrieve a product record by its ID.
- **Path Variable:**
    - **Name:** `productId`
    - **Type:** `String`
    - **Description:** ID of the product to be retrieved.
- **Response:**
    - **Type:** `ProductChannelDto`
    - **Description:** DTO representing the retrieved product record.
- **HTTP Status:** `200` (Product successfully retrieved.)

### Update Product by Product ID
- **Endpoint:** `/product/{productId}`
- **HTTP Method:** `PUT`
- **Description:** Update a product record by its ID.
- **Path Variable:**
    - **Name:** `productId`
    - **Type:** `String`
    - **Description:** ID of the product to be updated.
- **Request Payload:**
    - **Type:** `ProductUpdateRequestDto`
    - **Description:** DTO containing updated product details.
- **Response:**
    - **Type:** `ProductChannelDto`
    - **Description:** DTO representing the updated product record.
- **HTTP Status:** `200` (Product updated successfully.)

### Delete Product by Product ID
- **Endpoint:** `/product/{productId}`
- **HTTP Method:** `DELETE`
- **Description:** Delete a product record by its ID.
- **Path Variable:**
    - **Name:** `productId`
    - **Type:** `String`
    - **Description:** ID of the product to be deleted.
- **HTTP Status:** `204` (Product successfully deleted.)

### Dependencies:
- **Service:** `ProductService`

# Role Controller API Documentation

## Table of Contents
- [Create a New Role](#create-a-new-role)
- [Get Role by Role ID](#get-role-by-role-id)
- [Dependencies](#dependencies)

---

### Create a New Role
- **Endpoint:** `/user/role`
- **HTTP Method:** `POST`
- **Description:** Create a new user role.
- **Request Payload:**
    - **Type:** `RoleRequestDto`
    - **Description:** DTO containing role details required for creation.
- **Response:**
    - **Type:** `RoleChannelDto`
    - **Description:** DTO representing the created user role.
- **HTTP Status:** `201` (Role created successfully.)

### Get Role by Role ID
- **Endpoint:** `/user/role/{roleId}`
- **HTTP Method:** `GET`
- **Description:** Retrieve a user role by its ID.
- **Path Variable:**
    - **Name:** `roleId`
    - **Type:** `String`
    - **Description:** ID of the role to be retrieved.
- **Response:**
    - **Type:** `RoleChannelDto`
    - **Description:** DTO representing the retrieved user role.
- **HTTP Status:** `200` (Role successfully retrieved.)

### Dependencies:
- **Service:** `RoleService`

# UserController API Documentation

## Table of Contents
- [Create a New User](#create-a-new-user)
- [Get User by User ID](#get-user-by-user-id)
- [Update User by User ID](#update-user-by-user-id)
- [Delete User by User ID](#delete-user-by-user-id)
- [User Login](#user-login)
- [Dependencies](#dependencies)

---

### Create a New User
- **Endpoint:** `/user`
- **HTTP Method:** `POST`
- **Description:** Create a new user.
- **Request Payload:**
    - **Type:** `UserRequestDto`
    - **Description:** DTO containing user details required for creation.
- **Response:**
    - **Type:** `UserChannelDto`
    - **Description:** DTO representing the created user.
- **HTTP Status:** `201` (User created successfully.)

### Get User by User ID
- **Endpoint:** `/user/{userId}`
- **HTTP Method:** `GET`
- **Description:** Retrieve a user by their ID.
- **Path Variable:**
    - **Name:** `userId`
    - **Type:** `String`
    - **Description:** ID of the user to be retrieved.
- **Response:**
    - **Type:** `UserChannelDto`
    - **Description:** DTO representing the retrieved user.
- **HTTP Status:** `200` (User successfully retrieved.)

### Update User by User ID
- **Endpoint:** `/user/{userId}`
- **HTTP Method:** `PUT`
- **Description:** Update details of a user by their ID.
- **Path Variable:**
    - **Name:** `userId`
    - **Type:** `String`
    - **Description:** ID of the user to be updated.
- **Request Payload:**
    - **Type:** `UserUpdateRequestDto`
    - **Description:** DTO containing user details required for updating.
- **Response:**
    - **Type:** `UserChannelDto`
    - **Description:** DTO representing the updated user.
- **HTTP Status:** `200` (User updated successfully.)

### Delete User by User ID
- **Endpoint:** `/user/{userId}`
- **HTTP Method:** `DELETE`
- **Description:** Remove a user by their ID.
- **Path Variable:**
    - **Name:** `userId`
    - **Type:** `String`
    - **Description:** ID of the user to be deleted.
- **HTTP Status:** `204` (User successfully removed.)

### User Login
- **Endpoint:** `/login`
- **HTTP Method:** `POST`
- **Description:** User authentication and login.
- **Request Payload:**
    - **Type:** `UserLoginRequestDTO`
    - **Description:** DTO containing user login credentials.
- **Response:**
    - **Type:** `UserLoginResponseDto`
    - **Description:** DTO representing the user login response.
- **HTTP Status:** `200` (User logged in successfully.)

### Dependencies:
- **Service:** `UserService`


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`AWS_ACCESS_KEY_ID`

`AWS_SECRET_ACCESS_KEY`

`AWS_PROFILE`

`AWS_SESSION_TOKEN`

`AWS_COGNITO_JWKS`

`OAUTH2_ISSUER_URI`

`AWS_REGION`

`COGNITO_USER_POOL_ID`

`COGNITO_CLIENT_ID`

`MONGODB_READ_PREFERENCE`

`MONGODB_URI_ECOMMERCE`

`ECOMMERCE_SERVICE_API_KEY`

`SPRING_REDIS_HOST`

`SPRING_REDIS_PORT`

`SPRING_REDIS_TIMEOUT`

## Pre requirements

Cognito userPool, mongodb conection,  cache redis conection.

## Author

- [@Ventura Gorostieta Valentin](https://www.linkedin.com/in/ventura-gorostieta-valentin-612b29a2/)

## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

- Update and set VARs Env with your mongodb and redis server.
- Set AWS vars with your cognito configuration.

Clean project

```bash
  :clean
```

Build project

```bash
  :build
```

Run spring boot application
```bash
  :bootRun
```
## Running Tests

To run tests, run the following command

```bash
  :test
```

To run tests, run the following command with jacocoTestResport

```bash
  :jacocoTestReport
```
To run tests, run the following command with jacocoTestCoverageVerification

```bash
  :jacocoTestCoverageVerification
```
