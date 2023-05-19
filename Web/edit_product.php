<!DOCTYPE html>
<html>
<head>
  <title>Edit Product</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f7fa;
      color: #333;
      margin: 0;
      padding: 20px;
    }
    
    form {
      width: 400px;
      background-color: #ffffff;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      padding: 20px;
    }
    
    form label {
      display: block;
      margin-bottom: 10px;
    }
    
    form input[type="text"],
    form textarea {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 3px;
      margin-bottom: 15px;
    }
    
    form input[type="submit"] {
      background-color: #2170b8;
      color: #ffffff;
      padding: 10px 15px;
      border: none;
      border-radius: 3px;
      cursor: pointer;
    }
  </style>
</head>
<body>
  <?php
  
  if ($_SERVER["REQUEST_METHOD"] == "POST") {
   
    $product_id = $_POST["product_id"];
    
    
    $servername = "your_servername";
    $username = "your_username";
    $password = "your_password";
    $dbname = "your_database";

    // Create a connection
    $conn = new mysqli($servername, $username, $password, $dbname);

    // Check connection
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    // Fetch the product details from the database
    $sql = "SELECT name, description, price FROM products WHERE id = $product_id";
    $result = $conn->query($sql);

    if ($result->num_rows == 1) {
        $row = $result->fetch_assoc();
        $name = $row["name"];
        $description = $row["description"];
        $price = $row["price"];
    } else {
        echo "Product not found.";
        exit;
    }

    $conn->close();
    // End of database retrieval logic

    // Display the editing form with the current product details
    ?>
    <form action="update_product.php" method="POST">
      <input type="hidden" name="product_id" value="<?php echo $product_id; ?>">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" value="<?php echo $name; ?>" required>
      
      <label for="description">Description:</label>
      <textarea id="description" name="description" required><?php echo $description; ?></textarea>
      
      <label for="price">Price:</label>
      <input type="text" id="price" name="price" value="<?php echo $price; ?>" required>
      
      <input type="submit" value="Update">
    </form>
    <?php
  } else {
    echo "Invalid request.";
  }
  ?>
</body>
</html>
