<!DOCTYPE html>
<html>
<head>
  <title>Product Table</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f7fa;
      color: #333;
      margin: 0;
      padding: 20px;
    }
    
    table {
      width: 100%;
      border-collapse: collapse;
      background-color: #ffffff;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    
    table th,
    table td {
      padding: 10px;
      text-align: left;
    }
    
    table th {
      background-color: #2170b8;
      color: #ffffff;
      font-weight: bold;
      text-transform: uppercase;
    }
    
    table tr:nth-child(even) {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>
  <table>
    <thead>
      <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Product 1</td>
        <td>Description of Product 1</td>
        <td>$9.99</td>
        <td>
          <form action="edit_product.php" method="POST">
            <input type="hidden" name="product_id" value="1">
            <input type="submit" value="Edit">
          </form>
        </td>
      </tr>
      <tr>
        <td>Product 2</td>
        <td>Description of Product 2</td>
        <td>$14.99</td>
        <td>
          <form action="edit_product.php" method="POST">
            <input type="hidden" name="product_id" value="2">
            <input type="submit" value="Edit">
          </form>
        </td>
      </tr>
      <tr>
        <td>Product 3</td>
        <td>Description of Product 3</td>
        <td>$19.99</td>
        <td>
          <form action="edit_product.php" method="POST">
            <input type="hidden" name="product_id" value="3">
            <input type="submit" value="Edit">
          </form>
        </td>
      </tr>
      <!-- Additional rows as needed -->
    </tbody>
  </table>
</body>
</html>
