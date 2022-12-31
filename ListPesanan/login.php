<?php

$file_db = "login.db";

try{
    $pdo = new PDO("sqlite:$file_db");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);

    $sql_create = "CREATE TABLE IF NOT EXISTS 'user'(
        'id' integer NOT NULL PRIMARY KEY AUTOINCREMENT,
        'username' text NOT NULL, 
        'password' text NOT NULL, 
        'created_at' datetime NOT NULL DEFAULT CURRENT_TIMESTAMP)";
    $pdo->exec($sql_create);
}catch(PDOException $e){
    throw new PDOException($e->getMessage(), (int)$e->getCode());
}

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $query = 'select * from user order by created_at desc';
    $stmt = $pdo->prepare($query);
    $stmt->execute();
    $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($data);
}elseif ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];
    $query = 'select * from user where username = @username and password = @password';
    $stmt = $pdo->prepare($query);
    $stmt->execute();
    $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode(array('username'=>$username,'password'=>$password));
}