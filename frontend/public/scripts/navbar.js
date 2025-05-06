function navigate(page) {
    const pages = [
        'halamanGudangBeranda', 
        'halamanGudangDetail', 
        'halamanGudangPesanan', 
        'halamanGrafik', 
        'riwayat',
        'kontak'
    ];

    // Sembunyikan semua halaman
    pages.forEach(id => {
        const element = document.getElementById(id);
        if (element) {
            element.style.display = "none";
        }
    });

    // Tampilkan halaman yang dipilih
    const selectedPage = document.getElementById(page);
    if (selectedPage) {
        selectedPage.style.display = "block";
    }

    // Hapus kelas 'active' dari semua tombol
    document.querySelectorAll(".navbar button").forEach(button => {
        button.classList.remove("active");
    });

    // Cari tombol yang sesuai dengan ID halaman dan tambahkan kelas 'active'
    // Contoh: halamanGudangBeranda → buttonPenyimpanan
    const activeButtonId = {
        'halamanGudangBeranda': 'buttonPenyimpanan',
        'halamanGudangDetail': 'buttonServis',
        'halamanGudangPesanan': 'buttonPesanan',
        'halamanGrafik': 'buttonGrafik',
        'riwayat': 'buttonRiwayat',
        'kontak': 'buttonKontak'
    }[page];

    const activeButton = document.getElementById(activeButtonId);
    if (activeButton) {
        activeButton.classList.add("active");
    }
}