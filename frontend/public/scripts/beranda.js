/**
 * Loads the navbar component from an external HTML file
 */
export async function loadNavbar() {
  try {
    const navbarRes = await fetch('./navbar.html'); // path dari /pages ke /public
    const navbarHtml = await navbarRes.text();
    document.getElementById('navbar').innerHTML = navbarHtml;
  } catch (err) {
    console.error('Gagal memuat navbar:', err);
  }
}
function generateBoxes(boxCount, containerId) {
  const boxContainer = document.getElementById(containerId);
  if (!boxContainer) return;

  boxContainer.innerHTML = '';
  for (let i = 1; i <= boxCount; i++) {
    const box = document.createElement('div');
    box.className = 'item';
    box.textContent = i; // isi content diganti isi dari list
    boxContainer.appendChild(box);
  }
}
function initializeApp() {
  loadNavbar();
  if (document.getElementById('card')) {
    generateBoxes(9, 'card');// angka 9 diganti sebanyak panjang list
  }

  // Add event listener for add item button if it exists
  const addItemBtn = document.getElementById('addItemButton');
  if (addItemBtn) {
    addItemBtn.addEventListener('click', () => {
      alert('Fitur tambah item belum tersedia.');
    });
  }
}

document.addEventListener('DOMContentLoaded', initializeApp);

export { generateBoxes };