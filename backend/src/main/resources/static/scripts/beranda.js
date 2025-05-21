document.addEventListener('DOMContentLoaded', () => {
    const links = document.querySelectorAll('.item a');
    const overlay = document.getElementById('transitionOverlay');

    links.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault(); // hentikan sementara navigasi
            const targetUrl = link.getAttribute('href');

            // Aktifkan transisi
            overlay.classList.add('active');

            // Setelah transisi selesai, arahkan ke halaman target
            setTimeout(() => {
                window.location.href = targetUrl;
            }, 500); // waktu harus sesuai dengan durasi CSS transition
        });
    });
});
