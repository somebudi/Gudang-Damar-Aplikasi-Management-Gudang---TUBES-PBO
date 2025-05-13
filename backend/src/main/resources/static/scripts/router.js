export function navigate(page) {
  const pages = [
    'halamanGudangBeranda', 
    'halamanGudangDetail', 
    'halamanGudangPesanan', 
    'halamanGrafik', 
    'riwayat',
    'kontak'
  ];

  pages.forEach(id => {
    const element = document.getElementById(id);
    if (element) {
      element.style.display = "none";
    }
  });

  const selectedPage = document.getElementById(page);
  if (selectedPage) {
    selectedPage.style.display = "block";
  }

  const activeButtonId = {
    'halamanGudangBeranda': 'buttonPenyimpanan',
    'halamanGudangDetail': 'buttonServis',
    'halamanGudangPesanan': 'buttonPesanan',
    'halamanGrafik': 'buttonGrafik',
    'riwayat': 'buttonRiwayat',
    'kontak': 'buttonKontak'
  }[page];

  document.querySelectorAll(".navbar button").forEach(button => {
    button.classList.remove("active");
  });

  const activeButton = document.getElementById(activeButtonId);
  if (activeButton) {
    activeButton.classList.add("active");
  }
}
