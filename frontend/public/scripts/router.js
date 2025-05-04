// Konfigurasi halaman
const routes = {
  halamanRumah: {
    html: 'login.html',
  
  },
  halamanGudangBeranda: {
    html: 'halamanGudangBeranda.html',
  
  },
  halamanGudangDetail: {
    html: 'halamanGudangDetail.html',
   
  },
  halamanGudangPesanan: {
    html: 'halamanGudangPesanan.html',
   
  },
  halamanGrafik: {
    html: 'halamanGrafik.html',

  }
};

// Fungsi untuk memuat navbar
async function loadNavbar() {
  try {
    const navbarRes = await fetch('navbar.html');
    const navbarHtml = await navbarRes.text();
    document.getElementById('navbar').innerHTML = navbarHtml;
  } catch (err) {
    console.error('Gagal memuat navbar:', err);
  }
}

// Fungsi navigasi antar halaman
export async function navigate(page) {
  const route = routes[page];
  if (!route) return;

  const token = localStorage.getItem('authToken');

  // Cek autentikasi untuk halaman selain login
  if (page !== 'halamanRumah' && !token) {
    alert('Silakan login terlebih dahulu.');
    return navigate('halamanRumah');
  }

  try {
    const res = await fetch(route.html);
    const html = await res.text();
    document.getElementById("app").innerHTML = html;

    if (route.script) {
      const scriptEl = document.createElement("script");
      scriptEl.type = "module";
      scriptEl.src = route.script; // Gunakan path relatif
      document.body.appendChild(scriptEl);
    }
  } catch (err) {
    console.error('Gagal memuat halaman:', err);
  }
}

// Jangan langsung navigate ke dashboard di sini
// Biarkan halaman login yang mengatur navigasi setelah login

// Namun bisa dipakai jika kamu ingin memuat halaman default:
// window.addEventListener('DOMContentLoaded', () => {
//   loadNavbar();
//   navigate('halamanRumah'); // Atau halamanGudangDashboard jika sudah login
// });

// Buat tersedia global jika perlu
window.navigate = navigate;
