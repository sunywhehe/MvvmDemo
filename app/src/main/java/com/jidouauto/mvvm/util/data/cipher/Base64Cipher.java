package com.jidouauto.mvvm.util.data.cipher;

import com.jidouauto.mvvm.util.data.safe.Base64;

/**
 * @author yuyh.
 * @date 16/4/9.
 */
public class Base64Cipher extends BaseCipher {
    private BaseCipher cipher;

    public Base64Cipher() {
    }

    public Base64Cipher(BaseCipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public byte[] decrypt(byte[] res) {
        if (cipher != null) {
            res = cipher.decrypt(res);
        }
        return Base64.decode(res, Base64.DEFAULT);
    }

    @Override
    public byte[] encrypt(byte[] res) {
        if (cipher != null) {
            res = cipher.encrypt(res);
        }
        return Base64.encode(res, Base64.DEFAULT);
    }
}
