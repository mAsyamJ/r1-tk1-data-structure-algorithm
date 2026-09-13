/*
 * PlaylistOOP.java
 * Tugas Kelompok ke-1 - Data Structures and Algorithm Analysis
 * Week 3 - Introduction to OOP & Data Structures
 *
 * Anggota kelompok:
 * 1. Muhammad Asyam Jayanegara      - NIM: 2902817585
 * 2. Rismanto                       - NIM: 2902811940
 * 3. Primaaditya Redyananda         - NIM: 2902814450
 * 4. Amelia Rizqi Fadila            - NIM: 2902824546
 * 5. Putri Puspita Kartini Rahman   - NIM: 2902820314
 */

class Lagu {
    // Atribut private untuk menerapkan enkapsulasi.
    private String judul;
    private String artis;
    private double durasi;

    // Constructor untuk membuat objek Lagu.
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getArtis() {
        return artis;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    public double getDurasi() {
        return durasi;
    }

    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    // Menampilkan informasi lengkap satu lagu.
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.printf("Durasi : %.2f menit%n", durasi);
    }
}

class User {
    // Atribut private untuk menerapkan enkapsulasi pada data pengguna.
    private String nama;

    public User(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method parent yang dioverride oleh Admin dan Member.
    public void tampilkanAkses() {
        System.out.println("Akses User: belum memiliki hak akses khusus.");
    }
}

class Admin extends User {
    public Admin(String nama) {
        super(nama);
    }

    // Polymorphism: implementasi akses khusus Admin.
    @Override
    public void tampilkanAkses() {
        System.out.println("Akses Admin: menambahkan lagu dan melihat daftar lagu.");
    }

    // Menambahkan lagu pada indeks kosong pertama di array playlist.
    public int tambahLagu(Lagu[] playlist, int jumlahLagu, Lagu laguBaru) {
        if (laguBaru == null) {
            System.out.println("Lagu yang ditambahkan tidak boleh kosong.");
            return jumlahLagu;
        }

        if (jumlahLagu >= playlist.length) {
            System.out.println("Playlist sudah penuh. Lagu tidak dapat ditambahkan.");
            return jumlahLagu;
        }

        playlist[jumlahLagu] = laguBaru;
        System.out.println("Lagu \"" + laguBaru.getJudul() + "\" berhasil ditambahkan.");
        return jumlahLagu + 1;
    }

    // Menampilkan seluruh lagu yang aktif pada array playlist.
    public void lihatDaftarLagu(Lagu[] playlist, int jumlahLagu) {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }

        System.out.println("=== Daftar Lagu ===");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.println((i + 1) + ".");
            playlist[i].tampilkanInfo();
            System.out.println();
        }
    }
}

class Member extends User {
    public Member(String nama) {
        super(nama);
    }

    // Polymorphism: implementasi akses khusus Member.
    @Override
    public void tampilkanAkses() {
        System.out.println(
            "Akses Member: melihat daftar lagu, mencari lagu, dan menghitung rata-rata durasi."
        );
    }

    // Menelusuri dan menampilkan seluruh lagu pada playlist.
    public void lihatDaftarLagu(Lagu[] playlist, int jumlahLagu) {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }

        System.out.println("=== Daftar Lagu untuk Member ===");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.println((i + 1) + ".");
            playlist[i].tampilkanInfo();
            System.out.println();
        }
    }

    // Mencari lagu berdasarkan judul menggunakan linear search.
    public Lagu cariLagu(Lagu[] playlist, int jumlahLagu, String judul) {
        if (judul == null || judul.trim().isEmpty()) {
            System.out.println("Judul pencarian tidak boleh kosong.");
            return null;
        }

        String target = judul.trim();
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(target)) {
                System.out.println("Lagu ditemukan:");
                playlist[i].tampilkanInfo();
                return playlist[i];
            }
        }

        System.out.println("Lagu dengan judul \"" + judul + "\" tidak ditemukan.");
        return null;
    }

    // Menghitung rata-rata durasi seluruh lagu yang aktif pada playlist.
    public double hitungRataRataDurasi(Lagu[] playlist, int jumlahLagu) {
        if (jumlahLagu == 0) {
            return 0.0;
        }

        double totalDurasi = 0.0;
        for (int i = 0; i < jumlahLagu; i++) {
            totalDurasi += playlist[i].getDurasi();
        }

        return totalDurasi / jumlahLagu;
    }
}

public class PlaylistOOP {
    public static void main(String[] args) {
        // Array digunakan sebagai struktur data utama untuk menyimpan objek Lagu.
        Lagu[] playlist = new Lagu[10];
        int jumlahLagu = 0;

        Admin admin = new Admin("Admin Playlist");
        Member member = new Member("Member Playlist");

        System.out.println("========================================");
        System.out.println("   SISTEM MANAJEMEN PLAYLIST MUSIK");
        System.out.println("========================================");

        // Demonstrasi polymorphism melalui reference bertipe User.
        System.out.println("\n--- Demonstrasi Akses User ---");
        User[] daftarUser = {admin, member};
        for (User user : daftarUser) {
            System.out.print(user.getNama() + " -> ");
            user.tampilkanAkses();
        }

        // Admin menambahkan beberapa objek Lagu ke array playlist.
        System.out.println("\n--- Admin Menambahkan Lagu ---");
        jumlahLagu = admin.tambahLagu(
            playlist, jumlahLagu, new Lagu("Laskar Pelangi", "Nidji", 4.05)
        );
        jumlahLagu = admin.tambahLagu(
            playlist, jumlahLagu, new Lagu("Hati-Hati di Jalan", "Tulus", 4.02)
        );
        jumlahLagu = admin.tambahLagu(
            playlist, jumlahLagu, new Lagu("Bertaut", "Nadin Amizah", 5.16)
        );

        // Member melihat seluruh isi playlist.
        System.out.println("\n--- Member Melihat Daftar Lagu ---");
        member.lihatDaftarLagu(playlist, jumlahLagu);

        // Member mencari satu lagu berdasarkan judul.
        System.out.println("--- Member Mencari Lagu ---");
        member.cariLagu(playlist, jumlahLagu, "Hati-Hati di Jalan");

        // Member menghitung rata-rata durasi lagu dalam playlist.
        System.out.println("\n--- Rata-Rata Durasi Playlist ---");
        double rataRata = member.hitungRataRataDurasi(playlist, jumlahLagu);
        System.out.printf("Rata-rata durasi: %.2f menit%n", rataRata);

        System.out.println("\nProgram selesai dijalankan tanpa error.");
    }
}