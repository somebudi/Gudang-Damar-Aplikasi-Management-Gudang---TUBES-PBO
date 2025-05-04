// beranda.js
export async function loadNavbar() {
    try {
      const navbarRes = await fetch('.../navbar.html'); // path dari /pages ke /public
      const navbarHtml = await navbarRes.text();
      document.getElementById('navbar').innerHTML = navbarHtml;
    } catch (err) {
      console.error('Gagal memuat navbar:', err);
    }
  }
  
  document.addEventListener('DOMContentLoaded', () => {
    loadNavbar();
  
    // Tambah event listener lain kalau ada
    const addItemBtn = document.getElementById('addItemButton');
    if (addItemBtn) {
      addItemBtn.addEventListener('click', () => {
        alert('Fitur tambah item belum tersedia.');
      });
    }
  });
  