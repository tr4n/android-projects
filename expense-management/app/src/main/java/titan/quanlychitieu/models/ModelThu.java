package titan.quanlychitieu.models;

/**
 * Created by titan on 17/06/2017.
 */

public class ModelThu {
    private String tenkhoanthu,nguoitao,ghichu,ngaytao,sotien;




    public ModelThu(String tenkhoanthu, String nguoitao, String ngaytao,String sotien, String ghichu) {
        this.tenkhoanthu = tenkhoanthu;
        this.nguoitao = nguoitao;
        this.ngaytao = ngaytao;
        this.sotien = sotien;
        this.ghichu = ghichu;
    }

    public String getTenkhoanthu() {
        return tenkhoanthu;
    }

    public String getNguoitao() {
        return nguoitao;
    }

    public String getGhichu() {
        return ghichu;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public String getSotien() {
        return sotien;
    }

    public void setTenkhoanthu(String tenkhoanthu) {
        this.tenkhoanthu = tenkhoanthu;
    }

    public void setNguoitao(String nguoitao) {
        this.nguoitao = nguoitao;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }
}
