<?php

$file_db = "listpesanan.db";

try{
    $pdo = new PDO("sqlite:$file_db");
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);

    $sql_create = "CREATE TABLE IF NOT EXISTS 'list_pesanan'(
        'id' integer NOT NULL PRIMARY KEY AUTOINCREMENT,
        'namaMakanan' text NOT NULL, 
        'hargaMakanan' integer NOT NULL, 
        'jumlahMakanan' integer NOT NULL,
        'created_at' datetime NOT NULL DEFAULT CURRENT_TIMESTAMP)";
    $pdo->exec($sql_create);
}catch(PDOException $e){
    throw new PDOException($e->getMessage(), (int)$e->getCode());
}

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $query = 'select * from list_pesanan order by created_at desc';
    $stmt = $pdo->prepare($query);
    $stmt->execute();
    $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($data);
} elseif ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $namaMakanan = $_POST['namaMakanan'];
    $hargaMakanan = $_POST['hargaMakanan'];
    $jumlahMakanan = $_POST['jumlahMakanan'];
    $query = "insert into list_pesanan (namaMakanan, hargaMakanan, jumlahMakanan) values (?, ?, ?)";
    $stmt = $pdo->prepare($query);
    $res = $stmt->execute([$namaMakanan, $hargaMakanan, $jumlahMakanan]);
    if($res){
        $data = ['namaMakanan'=>$namaMakanan, 'hargaMakanan'=>$hargaMakanan, 'jumlahMakanan'=>$jumlahMakanan];
        echo json_encode($data);
    }else{
        echo json_encode(['error'=>$stmt->errorCode()]);
    }
} elseif ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
    $id = $_GET['id'];
    $query = "delete from list_pesanan where id = ?";
    $stmt = $pdo->prepare($query);
    $res = $stmt->execute([$id]);
    if($res){
        $data = ['id'=>$id];
        echo json_encode($data);
    }else{
        echo json_encode(['error'=>$stmt->errorCode()]);
    }
    
}