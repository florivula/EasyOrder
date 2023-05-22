<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['action']) && $_POST['action'] === 'sign-in') {
        // Sign-in
        $username = $_POST['username'];
        $password = $_POST['password'];

        $conn = new PDO("mysql:host=localhost;dbname=easyorder", "root", "");

        $query = "SELECT * FROM users WHERE username = :username AND password = :password";
        $stmt = $conn->prepare($query);
        $stmt->bindParam(':username', $username);
        $stmt->bindParam(':password', $password);

        $stmt->execute();

        if ($stmt->rowCount() > 0) {
            // per login t'suksesshem
            $_SESSION['username'] = $username;
            $_SESSION['business'] = $business;
            echo "<script>alert('Login successful');</script>";
            header("Location: admin_dashboard.html");

            exit();
        } else {
            echo "<script>alert('Invalid username or password');</script>";
        }
    } elseif (isset($_POST['action']) && $_POST['action'] === 'sign-up') {
        // Sign-up
        $username = $_POST['username'];
        $email = $_POST['email'];
        $business = $_POST['business'];
        $password = $_POST['password'];
        $confirmPassword = $_POST['confirm_password'];

        if (!empty($username) && !empty($email) && !empty($business) && !empty($password) && !empty($confirmPassword)) {
            if ($password === $confirmPassword) {
                $conn = new PDO("mysql:host=localhost;dbname=easyorder", "root", "");

                $query = "SELECT * FROM users WHERE username = :username OR email = :email";
                $stmt = $conn->prepare($query);
                $stmt->bindParam(':username', $username);
                $stmt->bindParam(':email', $email);
                $stmt->execute();

                if ($stmt->rowCount() > 0) {
                    echo "<script>alert('Username or email already exists');</script>";
                } else {
                    $query = "INSERT INTO users (username, email, business, password) VALUES (:username, :email, :business, :password)";
                    $stmt = $conn->prepare($query);
                    $stmt->bindParam(':username', $username);
                    $stmt->bindParam(':email', $email);
                    $stmt->bindParam(':business', $business);
                    $stmt->bindParam(':password', $password);
                    $stmt->execute();

                    echo "<script>alert('Sign-up successful');</script>";
                    header("Location: login.php");
                    exit();
                }
            } else {
                echo "<script>alert('Passwords do not match');</script>";
            }
        } else {
            echo "<script>alert('Please fill in all fields');</script>";
        }
    }
}
?>


<!DOCTYPE html>
<html>
<head>
	<link rel="icon" type="image/png" href="assets/img/name-logo.png">
    <title>EasyOrder</title>
    <link rel="stylesheet" href="./assets/css/login-style.css">
</head>
<body>
    <div id="container" class="container">
        <div class="row">
            <div class="col align-items-center flex-col sign-up">
                <div class="form-wrapper align-items-center">
                    <div class="form sign-up">
                        <form action="login.php" method="POST">
                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input type="text" placeholder="Username" name="username">
                            </div>
                            <div class="input-group">
                                <i class='bx bx-mail-send'></i>
                                <input type="email" placeholder="Email" name="email">
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input type="text" placeholder="Business name" name="business">
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-lock-alt'></i>
                                <input type="password" placeholder="Password" name="password">
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-lock-alt'></i>
                                <input type="password" placeholder="Confirm password" name="confirm_password">
                            </div>
                            <input type="hidden" name="action" value="sign-up">
                            <button type="submit">Sign up</button>
                            <p>
                                <span>Already have an account?</span>
                                <b onclick="toggle()" class="pointer">Sign in here</b>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col align-items-center flex-col sign-in">
                <div class="form-wrapper align-items-center">
                    <div class="form sign-in">
                        <form action="login.php" method="POST">
                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input type="text" placeholder="Username" name="username">
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-lock-alt'></i>
                                <input type="password" placeholder="Password" name="password">
                            </div>
                            <input type="hidden" name="action" value="sign-in">
                            <button type="submit">Sign in</button>
                            <p><b>Forgot password?</b></p>
                            <p>
                                <span>Don't have an account?</span>
                                <b onclick="toggle()" class="pointer">Sign up here</b>
                            </p>
                        </form>
                    </div>
                </div>
                <div class="form-wrapper">
                </div>
            </div>
        </div>
        <div class="row content-row">
            <div class="col align-items-center flex-col">
                <div class="text sign-in">
                    <h2>Welcome</h2>
                </div>
                <div class="img sign-in">
                </div>
            </div>
            <div class="col align-items-center flex-col">
                <div class="text sign-up">
                    <h2></h2>
                </div>
                <div class="img sign-up">
                    <img src="assets/img/signup-logo.png">
                </div>
            </div>
        </div>
    </div>
    <script src="./assets/js/login-script.js"></script>
</body>
</html>
