import { navigate } from './router.js';

document.addEventListener('DOMContentLoaded', function () {
  const form = document.querySelector('form');
  const usernameInput = form.querySelector('input[type="text"]');
  const passwordInput = form.querySelector('input[type="password"]');

  form.addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = usernameInput.value.trim();
    const password = passwordInput.value.trim();

    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
      });

      const data = await response.json();

      if (response.ok) {
        localStorage.setItem('loggedIn', 'true');
        location.assign('halamanGudangBeranda.html');

        alert('Login berhasil!');
        
      } else {
        alert('Login gagal: ' + (data.message || 'Unknown error'));
      }
    } catch (err) {
      console.error('Error:', err);
      alert('Terjadi kesalahan saat mencoba login.');
    }
  });
});
