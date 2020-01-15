package com.fagawee.fmvp.app.net.response;

/**
 * Created by Mr.Tian on 2019/5/14.
 */

public class DeviceValidateNetBean {

    /**
     * code : 2000
     * msg : 操作成功
     * seed : 0ea9f79e64929297d4f362236f66d2d5
     * data : pgRSW8GJIjE0NjkxJWgLYpkqA1CswcQYzfP6aRMGwyjuFz/7Hu0gVt2VfLDgSwcAq+r4d3YVTxfB
     qACrktw+ocgI9e3W4zm5dOHqu153jA4+RI9mU35BTFeC/WxKObpf3ZV8sOBLBwDonbf7iKy5Ibgm
     SvsxiZFFlR7xCKaQN8LQrs19QJ89/yGEQGJYD7C+P46qtDC5rn2+dNswYEaeXaJCMqh1P9C7ExMg
     SkkMI5Vj4b2cinY1daZWScOCf0ePpJ8w157FtLf2br3JxxPVNiwc2gullg72oG1MYHebywB4vQw0
     w3ktrNfpXwbbYexaBeiHOjdrXP75JObadncbPyBcm95zmKh85M6NM6WySSaSoMebNKTGPqnVJhlC
     neclBcuPMdjkxDzpDkZjgewO+3uGvNFEysRaRS6Fc6TauwoOA778kJ220l4amug6AC6bDoTIeNBA
     g2Be6O8Rg63dYurXist6ZkBNvrEZPxvYu3gLvHkf7M0yLUFp/CF80/uMgqvW3LCAIpq9bpUlRnm1
     DXnB4DeX7+uitZr9iK1NtoC4lKin9Nz05B8NR6bH+Jb/1nDtmBsf4crk2lsHiG7k2LPqPdL/kXYB
     Gr2//tLjGbk+dQ/rGWWMepxCgaETo+xPP+a59GRRVxuKMFi10Sx9h9RNbqsIQK0xrwMCltSYTjeT
     ULWP9w9zIK123o6oDnRmjY8fR4B+J9A4L4el15pcFC2Fd6ajNcS8X1XUZ452E0We93zQz4moIPaB
     EPycU3+o+W8/k/Mby/RICQp6/Idu03qhlknzNh9K+9WEyuGb9TjW3RTUxWi9uuJBA9+/p3Dn3nu7
     Qsd1rdAWAV/PcDAwDeknSY0kInx/9Fiem4JX0q0=
     */

    private int code;
    private String msg;
    private String seed;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DeviceValidateNetBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", seed='" + seed + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
