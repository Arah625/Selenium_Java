package Utilities;

import java.util.Random;

public enum Language {

    AZERBAIJANI("Azerbaijani", "Azərbaycanca", "AZ"),
    INDONESIAN("Azerbaijani", "Bahasa Indonesia", "ID"),
    CATALAN("Catalan", "Catalan", "CA"),
    CZECH("Czech", "Česky", "CS"),
    DANISH("Danish", "Dansk", "DA"),
    GERMAN("German", "Deutsch", "DE"),
    ESTONIAN("Estonian", "Eesti", "ET"),
    ENGLISH("English", "English", "EN"),
    SPANISH("Spanish", "Español", "ES"),
    FRENCH("French", "Français", "FR"),
    IRISH("Irish", "Gaeilge", "GA"),
    ITALIAN("Italian", "Italiano", "IT"),
    POLISH("Polish", "Język Polski", "PL"),
    LATVIAN("Latvian", "Latvijas", "LV"),
    HUNGARIAN("Hungarian", "Magyar", "HU"),
    DUTCH("Dutch", "Nederlands", "NL"),
    NORWEGIAN("Norwegian", "Norsk", "NO"),
    PORTUGUESE("Portuguese", "Português", "PT"),
    BRAZILIAN_PORTUGUESE("Brazilian Portuguese", "Português (Brasil)", "BR"),
    RUSSIAN("Russian", "Pусский", "RU"),
    ROMANIAN("Romanian", "Română", "RO"),
    SWISS("Swiss", "Schwizerdütsch", "CH"),
    FINNISH("Finnish", "Suomalainen", "FI"),
    SWEDISH("Swedish", "Svenska", "SV"),
    TURKISH("Turkish", "Türkçe", "TR"),
    GREEK("Greek", "Ελληνικά", "EL"),
    BULGARIAN("Bulgarian", "български (език)", "BG"),
    UKRAINIAN("Ukrainian", "український", "UA"),
    GEORGIAN("Georgian", "ქართული", "KA"),
    HEBREW("Hebrew", "עברית", "HE"),
    ARABIC("Arabic", "عربي", "AR"),
    HINDI("Hindi", "हिंदी", "HI"),
    SINHALA("Sinhala", "සිංහල", "SI"),
    THAI("Thai", "ไทย", "TH"),
    BURMESE("Burmese", "မြန်မာ", "MY"),
    KOREAN("Korean", "한국어", "KO"),
    CHINESE("Chinese", "中文", "CN"),
    JAPANESE("Japanese", "日本語", "JA"),
    HONG_KONG("Hong Kong", "繁體中文", "HK"),
    TAIWANESE("Taiwanese", "繁體中文", "TW");
    private static final Random random = new Random();
    private final String englishName;
    private final String localName;
    private final String isoAlphaTwoCode;


    Language(String englishName, String localName, String isoAlphaTwoCode) {
        this.englishName = englishName;
        this.localName = localName;
        this.isoAlphaTwoCode = isoAlphaTwoCode;
    }

    public static String getRandomLocalName() {
        Language[] languages = Language.values();
        int randomIndex = random.nextInt(languages.length);
        return languages[randomIndex].getLocalName();
    }

    public static String getIsoAlphaTwoCodeByLocalName(String localName) {
        for (Language language : Language.values()) {
            if (language.getLocalName().equals(localName)) {
                return language.getIsoAlphaTwoCode();
            }
        }
        return null;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getLocalName() {
        return localName;
    }

    public String getIsoAlphaTwoCode() {
        return isoAlphaTwoCode;
    }
}
