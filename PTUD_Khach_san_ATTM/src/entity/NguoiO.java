package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NguoiO {
    private String hoTen;
    private LocalDate ngaySinh;
    private String SDT;
    private String CCCD;

    public NguoiO(String hoTen, LocalDate ngaySinh, String SDT, String CCCD) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.SDT = SDT;
        this.CCCD = CCCD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NguoiO nguoiO = (NguoiO) o;
        return Objects.equals(getCCCD(), nguoiO.getCCCD());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCCCD());
    }
}