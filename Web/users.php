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
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="icon" type="image/png" href="assets/img/name-logo.png">

  <title>

    Users

  </title>

  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />

  <!-- Nucleo Icons -->
  <link href="./assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="./assets/css/nucleo-svg.css" rel="stylesheet" />

  <!-- Font Awesome Icons -->
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">


  <!-- Material Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">

  <!-- CSS Files -->
  <link id="pagestyle" href="./assets/css/users.css" rel="stylesheet" />
  <link id="pagestyle" href="./assets/css/material-dashboard.css" rel="stylesheet" />
  
  <!-- Nepcha Analytics (nepcha.com) -->
  <!-- Nepcha is a easy-to-use web analytics. No cookies and fully compliant with GDPR, CCPA and PECR. -->
  <!--<script defer data-site="YOUR_DOMAIN_HERE" src="https://api.nepcha.com/js/nepcha-analytics.js"></script>-->

</head>


<body class="g-sidenav-show  bg-gray-100">





  <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">

    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <a class="navbar-brand m-0" href="">
        <img src="assets/img/logo.png" class="navbar-brand-img h-100" alt="main_logo">
        <span class="ms-1 font-weight-bold text-white">EasyOrder</span>
      </a>
    </div>


    <hr class="horizontal light mt-0 mb-2">

    <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
      <ul class="navbar-nav">

        <li class="nav-item">
          <a class="nav-link text-white " href="admin_dashboard.html">

            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">dashboard</i>
            </div>

            <span class="nav-link-text ms-1">Dashboard</span>
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link text-white " href="users.php">

            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">table_view</i>
            </div>

            <span class="nav-link-text ms-1">Users</span>
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link text-white " href="products.php">

            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">table_view</i>
            </div>

            <span class="nav-link-text ms-1">Products</span>
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link text-white " href="">

            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">table_view</i>
            </div>

            <span class="nav-link-text ms-1">Tables</span>
          </a>
        </li>

        <li class="nav-item mt-3">
          <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Manage Account</h6>
        </li>

        <li class="nav-item">
          <a class="nav-link text-white " href="profile.php">

            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">person</i>
            </div>

            <span class="nav-link-text ms-1">Profile</span>
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link text-white " href="">

            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">login</i>
            </div>

            <span class="nav-link-text ms-1">Switch Accounts</span>
          </a>
        </li>

      </ul>
    </div>

    <div class="sidenav-footer position-absolute w-100 bottom-0 ">
      <div class="mx-3">
        <!-- le qita mes na vyn nashta najsan kqyre provo qysh doket mujim mi lon si pull per "Change Accounts"
      <a class="btn btn-outline-primary mt-4 w-100" href="" type="button">ni emen qitu</a>
      -->
        <a class="btn bg-gradient-primary w-100" href="login.php" type="button">Sign Out</a>
      </div>

    </div>

  </aside>

  <main class="main-content border-radius-lg ">
    <!-- Navbar -->

    <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" data-scroll="true">
      <div class="container-fluid py-1 px-3">
        <nav aria-label="breadcrumb">

        </nav>
        <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
          <div class="ms-md-auto pe-md-3 d-flex align-items-center">

            <div class="input-group input-group-outline">
              <label class="form-label">Type here...</label>
              <input type="text" class="form-control">
            </div>

          </div>
          <ul class="navbar-nav  justify-content-end">
            <li class="nav-item d-xl-none ps-3 d-flex align-items-center">
              <a href="" class="nav-link text-body p-0" id="iconNavbarSidenav">
                <div class="sidenav-toggler-inner">
                  <i class="sidenav-toggler-line"></i>
                  <i class="sidenav-toggler-line"></i>
                  <i class="sidenav-toggler-line"></i>
                </div>
              </a>
            </li>
            <li class="nav-item px-3 d-flex align-items-center">
              <a href="" class="nav-link text-body p-0">
                <i class="fa fa-search fixed-plugin-button-nav cursor-pointer"></i>
              </a>
            </li>
        </div>
      </div>
    </nav>

    <!-- End Navbar -->

    <div class="container-fluid py-4">

      <div class="row">
        <div class="col-md-12">
          <div class="card">
            <div class="card-body">
              <h6 class="card-title text-uppercase mb-0">Manage Users</h6>
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
                        <h5 class="font-medium"><?php echo $user['username']; ?></h5>
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

  </main>

  <div class="fixed-plugin">
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
      <i class="material-icons py-2">settings</i>
    </a>
  </div>

  <!--   Core JS Files   -->
  <script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
      var options = {
        damping: '0.5'
      }
      Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
  </script>

  <!-- Github buttons -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>


  <script>
    function deleteUser(userId) {
      if (confirm("Are you sure you want to delete this user?")) {
        window.location.href = "<?php echo $_SERVER['PHP_SELF']; ?>?delete_user=" + userId;
      }
    }
  </script>
</body>

</html>