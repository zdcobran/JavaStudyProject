-----------------------------------------------
ERProducts 0.2 alpha
Authors: 
Elad yarkoni - 036690840
Alon Pisnoy - 310529912
-----------------------------------------------

-----------------------------------------------
Important information
-----------------------------------------------
1. default admin user:
    user: admin
    password: 123456

-----------------------------------------------
Authors comments
-----------------------------------------------

- The product implementation contains several parts

  1. The System classes like: Category, Order, Product and User types like:
     Administrator User, ReadonlyUser and ReadWrite user.
  2. The ObjectSystem class manage the Object lists in this program like products, orders, users and categories.
  3. Operations classes like OrderOps, ProductOps and UserOps that contain the whole product opeartions
  5. Each class has an interface that initialized by central Service
  6. Reports: Internal and system which handle the program report system
  7. Menus: contains the Product menus. at this time, just textual menus
  8. DB: The DB is access by persistent menager
  9. The project DB is sample(APP APP) the default one

- Premises

  1. there are independent lists like orders, products and categories
     and the connection between user-order or category-product represented by user property under Order object
     and category property under product object.
  2. The product user messages can be forward in the future to a log file so the whole messages are passing through
     our special Report System that can be changed once.
  3. The default admin user is created in DB when the project is starts (if not exists)
  4. When read only user is deleted, it's orders remain in the orders for history
  
 -DB scheme
 
	Orders
	Key: Generated ID
	OneToOne: To user
	OneToMany: To Product
	
	User
	Has: intertance to Admin, ReadWrite and Read only
	Key: Uusername
	
	Product
	Key:name
	OneToOne: Category
	
	Category
	Key:Generated ID
	
	Joined_Order_Products
	Key: oreder_id, product_name
	Auto generated table from the one to many relationship
  