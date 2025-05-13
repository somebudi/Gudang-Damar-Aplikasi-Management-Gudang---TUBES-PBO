document.addEventListener("DOMContentLoaded", () => {
    fetch('/barang')
        .then(response => {
            if (!response.ok) {
                throw new Error('Gagal mengambil data');
            }
            return response.json();
        })
        .then(data => {
            const container = document.getElementById("card");
            data.forEach(barang => {
                const itemDiv = document.createElement("div");
                itemDiv.className = "item listBarang";

                itemDiv.innerHTML = `
                    <p><strong>Nama:</strong> ${barang.namaBarang}</p>
                    <p><strong>Stok:</strong> ${barang.stok}</p>
                    <p><strong>Kategori:</strong> ${barang.kategori?.namaKategori ?? '-'}</p>
                    <p><strong>Masuk:</strong> ${formatTimestamp(barang.waktuMasuk)}</p>
                    <p><strong>Keluar:</strong> ${formatTimestamp(barang.waktuKeluar)}</p>
                    <p><strong>Pendataan:</strong> ${formatTimestamp(barang.waktuPendataan)}</p>
                    <p><strong>Supplier:</strong> ${barang.namaSupplier}</p>
                `;

                container.appendChild(itemDiv);
            });
        })
        .catch(error => {
            console.error('Terjadi kesalahan:', error);
        });
});

function formatTimestamp(timestamp) {
    if (!timestamp) return "-";
    const date = new Date(timestamp);
    return date.toLocaleString("id-ID");
}
