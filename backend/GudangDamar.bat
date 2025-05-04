@echo off
title Menjalankan Gudang Damar + Apache + MySQL

echo -------------------------------------
echo Menyalakan Apache dan MySQL dari XAMPP...
echo -------------------------------------

:: Ubah path ke XAMPP kamu kalau berbeda
cd /d C:\xampp

:: Menyalakan Apache dan MySQL
start "" apache_start.bat
start "" mysql_start.bat

timeout /t 5 > nul

echo -------------------------------------
echo Menyalakan Spring Boot...
echo -------------------------------------

cd /d D:\gudangdamar\gudangbe\backend
start "Spring Boot" cmd /k "mvn spring-boot:run"

timeout /t 8 > nul

echo -------------------------------------
echo Membuka halaman login di browser...
echo -------------------------------------

start http://127.0.0.1:5500/frontend/public/pages/login.html

exit
