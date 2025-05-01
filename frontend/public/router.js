// Menyimpan konfigurasi rute
const routes = {
    halamanRumah: {
      html: 'pages/login.html',
      script: 'scripts/login.js'
    },
    halamanGudangDashboard: {
      html: 'pages/halamanGudangBeranda.html',
      script: 'scripts/beranda.js'
    },
    halamanGudangDetail: {
      html: 'pages/halamanGudangDetail.html',
      script: 'scripts/detail.js'
    },
    halamanGudangPesanan: {
      html: 'pages/halamanGudangPesanan.html',
      script: 'scripts/pesanan.js'
    },
    halamanGrafik: {
      html: 'pages/halamanGrafik.html',
      script: 'scripts/grafik.js'
    }
  };
  
  // Fungsi untuk memuat navbar dan halaman
  async function loadNavbar() {
    const navbarRes = await fetch('navbar.html'); // Memuat navbar dari file terpisah
    const navbarHtml = await navbarRes.text();
    document.getElementById('navbar').innerHTML = navbarHtml;
  }
  
  // Fungsi untuk navigasi ke halaman yang sesuai
  export async function navigate(page) {
    const route = routes[page];
    if (!route) return;
  
    // Memuat halaman
    const res = await fetch(route.html);
    const html = await res.text();
    document.getElementById("app").innerHTML = html;
  
    // Memuat script untuk halaman tersebut
    if (route.script) {
      const scriptEl = document.createElement("script");
      scriptEl.type = "module";
      scriptEl.src = route.script;
      document.body.appendChild(scriptEl);
  }
  
  // Memuat navbar terlebih dahulu dan halaman default
  loadNavbar();
  navigate('halamanRumah'); // Halaman pertama yang dimuat saat aplikasi dijalankan
  window.navigate = navigate; // Membuat fungsi navigate bisa dipanggil dari HTML
  }  