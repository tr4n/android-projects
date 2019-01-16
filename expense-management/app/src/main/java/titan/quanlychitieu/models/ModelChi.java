package titan.quanlychitieu.models;

/**
 * Created by titan on 17/06/2017.
 */

public class ModelChi {
    private String tenkhoanchi,nguoitao,ghichu,ngaytao,sotien;




    public ModelChi(String tenkhoanchi, String nguoitao, String ngaytao,String sotien, String ghichu) {
        this.tenkhoanchi = tenkhoanchi;
        this.nguoitao = nguoitao;
        this.ngaytao = ngaytao;
        this.sotien = sotien;
        this.ghichu = ghichu;
    }

    public String gettenkhoanchi() {
        return tenkhoanchi;
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

    public void settenkhoanchi(String tenkhoanchi) {
        this.tenkhoanchi = tenkhoanchi;
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
