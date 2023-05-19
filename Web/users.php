<?php
$conn = new PDO("mysql:host=localhost;dbname=easyorder", "root", "");

// Delete
if (isset($_GET['delete_user'])) {
    $userId = $_GET['delete_user'];

    $query = "DELETE FROM users WHERE id = :id";
    $stmt = $conn->prepare($query);
    $stmt->bindParam(':id', $userId);
    $stmt->execute();
}

$query = "SELECT * FROM users";
$stmt = $conn->prepare($query);
$stmt->execute();
$users = $stmt->fetchAll(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="assets/img/name-logo.png">
    <title>Users</title>
    <!-- CSS Files -->
    <link id="pagestyle" href="./assets/css/users.css" rel="stylesheet" />
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title text-uppercase mb-0">Manage Users</h5>
                    </div>
                    <div class="table-responsive">
                        <table class="table no-wrap user-table mb-0">
                          <thead>
                            <tr>
                              <th scope="col" class="border-0 text-uppercase font-medium pl-4">#</th>
                              <th scope="col" class="border-0 text-uppercase font-medium">Name</th>
                              <th scope="col" class="border-0 text-uppercase font-medium">Email</th>
                              <th scope="col" class="border-0 text-uppercase font-medium">Role</th>
                              <th scope="col" class="border-0 text-uppercase font-medium">Manage</th>
                            </tr>
                          </thead>
                          <tbody>
                            <?php foreach ($users as $index => $user) { ?>
                              <tr>
                                <td class="pl-4"><?php echo $index + 1; ?></td>
                                <td>
                                    <h5 class="font-medium mb-0"><?php echo $user['username']; ?></h5>
                                </td>
                                <td>
                                    <span class="text-muted"><?php echo $user['email']; ?></span><br>
                                </td>
                                <td>
                                  <select class="form-control Role-select" id="exampleFormControlSelect1">
                                    <option>Owner</option>
                                    <option>Manager</option>
                                    <option>Waiter</option>
                                  </select>
                                </td>
                                <td>
                                  <button type="button" class="btn btn-outline-info btn-circle btn-lg btn-circle ml-2" onclick="deleteUser('<?php echo $user['id']; ?>')">
                                    <i class="fa fa-trash"></i>
                                  </button>
                                  <button type="button" class="btn btn-outline-info btn-circle btn-lg btn-circle ml-2"><i class="fa fa-edit"></i></button>
                                  <button type="button" class="btn btn-outline-info btn-circle btn-lg btn-circle ml-2"><i class="fa fa-save"></i></button>
                                </td>
                              </tr>
                            <?php } ?>
                          </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
    function deleteUser(userId) {
        if (confirm("Are you sure you want to delete this user?")) {
            window.location.href = "<?php echo $_SERVER['PHP_SELF']; ?>?delete_user=" + userId;
        }
    }
    </script>
</body>
</html>
