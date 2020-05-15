/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     10/14/2017 10:58:02 PM                       */
/*==============================================================*/


drop table if exists CHITIETHD;

drop table if exists CHITIETSPNHAP;

drop table if exists HOADON;

drop table if exists KHUYENMAI;

drop table if exists LOAISANPHAM;

drop table if exists NHACUNGCAP;

drop table if exists NHANVIEN;

drop table if exists QUYEN;

drop table if exists SANPHAM;

drop table if exists SANPHAM_KHUYENMAI;

drop table if exists SANPHAM_NCC;

drop table if exists VAITRO;

drop table if exists VAITRO_QUYEN;

/*==============================================================*/
/* Table: CHITIETHD                                             */
/*==============================================================*/
create table CHITIETHD
(
   SP_MA                char(3) not null,
   HD_MA                char(10) not null,
   CTHD_STT             smallint not null,
   CTHD_SOLUONG         float not null,
   CTHD_TONGTIEN        float not null,
   primary key (SP_MA, HD_MA)
);

/*==============================================================*/
/* Table: CHITIETSPNHAP                                         */
/*==============================================================*/
create table CHITIETSPNHAP
(
   SP_MA                char(3) not null,
   CTSPN_NGAYNHAP       date not null,
   CTSPN_DONGIA         float,
   CTSPN_SOLUONG        int,
   primary key (SP_MA, CTSPN_NGAYNHAP)
);

/*==============================================================*/
/* Table: HOADON                                                */
/*==============================================================*/
create table HOADON
(
   HD_MA                char(10) not null,
   NV_MA                smallint not null,
   HD_THOIGIANLAP       datetime not null,
   primary key (HD_MA)
);

/*==============================================================*/
/* Table: KHUYENMAI                                             */
/*==============================================================*/
create table KHUYENMAI
(
   KM_MA                int not null,
   KM_NGAYBD            date not null,
   KM_NGAYKT            date not null,
   KM_HINHTHUC          char(50) not null,
   KM_DONVI             float not null,
   primary key (KM_MA)
);

/*==============================================================*/
/* Table: LOAISANPHAM                                           */
/*==============================================================*/
create table LOAISANPHAM
(
   LSP_MA               char(2) not null,
   LSP_TEN              varchar(32) not null,
   LSP_DVT              char(55) not null,
   primary key (LSP_MA)
);

/*==============================================================*/
/* Table: NHACUNGCAP                                            */
/*==============================================================*/
create table NHACUNGCAP
(
   NCC_MA               char(5) not null,
   NCC_TEN              varchar(32) not null,
   NCC_DIACHI           char(100) not null,
   NCC_SDT              char(11) not null,
   NCC_MST              char(11) not null,
   NCC_SOFAX            char(10) not null,
   NCC_NGUOILH          char(50) not null,
   NCC_STK              char(20) not null,
   primary key (NCC_MA)
);

/*==============================================================*/
/* Table: NHANVIEN                                              */
/*==============================================================*/
create table NHANVIEN
(
   NV_MA                smallint not null,
   VT_MA                char(2) not null,
   NV_TEN               char(32) not null,
   NV_DIACHI            char(32) not null,
   NV_SDT               int not null,
   NV_EMAIL             varchar(100) not null,
   NV_GIOITINH          bool not null,
   NV_STK               char(20) not null,
   NV_MATKHAU           char(50) not null,
   NV_TENTK             char(60) not null,
   primary key (NV_MA)
);

/*==============================================================*/
/* Table: QUYEN                                                 */
/*==============================================================*/
create table QUYEN
(
   QUYEN_MA             smallint not null,
   QUYEN_DIENGIAI       char(30) not null,
   primary key (QUYEN_MA)
);

/*==============================================================*/
/* Table: SANPHAM                                               */
/*==============================================================*/
create table SANPHAM
(
   SP_MA                char(3) not null,
   LSP_MA               char(2) not null,
   SP_TEN               varchar(32) not null,
   primary key (SP_MA)
);

/*==============================================================*/
/* Table: SANPHAM_KHUYENMAI                                     */
/*==============================================================*/
create table SANPHAM_KHUYENMAI
(
   SP_MA                char(3) not null,
   KM_MA                int not null,
   primary key (SP_MA, KM_MA)
);

/*==============================================================*/
/* Table: SANPHAM_NCC                                           */
/*==============================================================*/
create table SANPHAM_NCC
(
   NCC_MA               char(5) not null,
   SP_MA                char(3) not null,
   primary key (NCC_MA, SP_MA)
);

/*==============================================================*/
/* Table: VAITRO                                                */
/*==============================================================*/
create table VAITRO
(
   VT_MA                char(2) not null,
   VT_TEN               varchar(32) not null,
   primary key (VT_MA)
);

/*==============================================================*/
/* Table: VAITRO_QUYEN                                          */
/*==============================================================*/
create table VAITRO_QUYEN
(
   VT_MA                char(2) not null,
   QUYEN_MA             smallint not null,
   primary key (VT_MA, QUYEN_MA)
);

alter table CHITIETHD add constraint FK_CHITIET_HOADON foreign key (HD_MA)
      references HOADON (HD_MA) on delete restrict on update restrict;

alter table CHITIETHD add constraint FK_SP_CTHD foreign key (SP_MA)
      references SANPHAM (SP_MA) on delete restrict on update restrict;

alter table CHITIETSPNHAP add constraint FK_SPNHAP_CHITIET foreign key (SP_MA)
      references SANPHAM (SP_MA) on delete restrict on update restrict;

alter table HOADON add constraint FK_HOADON_NHANVIEN foreign key (NV_MA)
      references NHANVIEN (NV_MA) on delete restrict on update restrict;

alter table NHANVIEN add constraint FK_CO_VAITRO foreign key (VT_MA)
      references VAITRO (VT_MA) on delete restrict on update restrict;

alter table SANPHAM add constraint FK_SANPHAM_LOAI foreign key (LSP_MA)
      references LOAISANPHAM (LSP_MA) on delete restrict on update restrict;

alter table SANPHAM_KHUYENMAI add constraint FK_SANPHAM_KHUYENMAI foreign key (SP_MA)
      references SANPHAM (SP_MA) on delete restrict on update restrict;

alter table SANPHAM_KHUYENMAI add constraint FK_SANPHAM_KHUYENMAI2 foreign key (KM_MA)
      references KHUYENMAI (KM_MA) on delete restrict on update restrict;

alter table SANPHAM_NCC add constraint FK_SANPHAM_NCC foreign key (NCC_MA)
      references NHACUNGCAP (NCC_MA) on delete restrict on update restrict;

alter table SANPHAM_NCC add constraint FK_SANPHAM_NCC2 foreign key (SP_MA)
      references SANPHAM (SP_MA) on delete restrict on update restrict;

alter table VAITRO_QUYEN add constraint FK_VAITRO_QUYEN foreign key (VT_MA)
      references VAITRO (VT_MA) on delete restrict on update restrict;

alter table VAITRO_QUYEN add constraint FK_VAITRO_QUYEN2 foreign key (QUYEN_MA)
      references QUYEN (QUYEN_MA) on delete restrict on update restrict;

