package asshohabah_borneo.cv.lapaksampit.Server;

import java.util.HashMap;
import java.util.Map;

public class Endpoints {

    /**
     * SETTING URL, DIRECTORY, PATH HOST
     * IP Address :
     * Genymotion = http://10.0.3.2/lapaksampit/
     * hostingan yang digunakan = http://lapaksampit.mataharimedia.com/
     */
    public static String Base_URL               = "http://10.0.2.2/lapaksampit/";

    /**
     * Setting tags JSON pada PHP
     * Sebelah Kiri Tag Untuk Java, Sebelah kanan Tag yang ada di PHP (Harus Sama)
     */

    /**
     * Table Produk.
     */
    public static String Produk_URL         = Base_URL+"produk.php";
    public static String Produk_Special_URL = Base_URL+"produk_special.php";
    public static String Produk_ID          = "id_produk";
    public static String Produk_KD          = "kd_produk";
    public static String Produk_NM          = "nm_produk";
    public static String Produk_GBR         = "gbr_produk";
    public static String Produk_Keterangan  = "keterangan";
    public static String Produk_Harga       = "harga";

    /**
     * Table Kategori
     */
    public static String Kategori_KD        = "kd_kategori";
    public static String Kategori_NM        = "nm_kategori";

    /**
     * Table Pengguna
     */

    public static String Pengguna_KD        = "kd_pengguna";
    public static String Pengguna_NM        = "nm_pengguna";
    public static String Pengguna_Alamat    = "alamat";
    public static String Pengguna_No_Telp   = "no_telp";
    public static String Pengguna_No_WA     = "no_wa";
    public static String Pengguna_Username  = "username";
    public static String Pengguna_Password  = "password";
    public static String Pengguna_Kunci     = "kunci_akses";

    /**
     * Definisi URL
     */
    public static String Pengguna_Login_URL             = Base_URL+"pengguna/login.php";
    public static String Pengguna_Register_URL          = Base_URL+"pengguna/register.php";
    public static String Pengguna_GetInformation_URL    = Base_URL+"pengguna/getinformation.php?username=";

    /**
     * Mendefinisikan Tags SharedPreference, Jika di PHP Kita Mengenal sebagai Session
     */
    public static final String TAG_JSON_ARRAY       = "result";
    public static final String LOGIN_SUCCESS        = "success";
    public static final String SharedPref_Nama      = "lapaksampitlogin";
    public static final String SharedPref_KD        = "sp_kd_pengguna";
    public static final String SharedPref_NM        = "sp_nm_pengguna";
    public static final String SharedPref_Alamat    = "sp_alamat";
    public static final String SharedPref_No_Telp   = "sp_no_telp";
    public static final String SharedPref_No_WA     = "sp_no_wa";
    public static final String SharedPref_Username  = "sp_username";
    public static final String SharedPref_Password  = "sp_password";
    public static final String SharedPref_Kunci     = "sp_kunci_akses";
    public static final String SharedPref_Loggedin  = "penggunalogin";
    public static Boolean loggedIn                  = false;


    public static final String[] Public_KD = new String[1];
    public static final String[] Public_NM = new String[1];
    public static final String[] Public_Username = new String[1];
    public static final String[] Public_Password = new String[1];
    public static final String[] Public_Alamat = new String[1];
    public static final String[] Public_No_Telp = new String[1];
    public static final String[] Public_No_Wa = new String[1];
    public static final String[] Public_Kunci = new String[1];
}
