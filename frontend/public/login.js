async function kirimForm(event) {
    event.preventDefault(); // Mencegah submit default
  
    const data = {
      name: document.querySelector('input[name="name"]').value,
      email: document.querySelector('input[name="email"]').value
    };
  
    console.log("Data yang dikirim:", data);
  
    try {
      const res = await fetch('http://localhost:8080/users', { // Arahkan ke backend Java!
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });
  
      const text = await res.text();
      alert("Server bilang: " + text);
    } catch (err) {
      console.error("Fetch error:", err);
      alert("Gagal kirim data ke server!");
    }
  }
  
  document.querySelector('#userForm').addEventListener('submit', kirimForm);
  