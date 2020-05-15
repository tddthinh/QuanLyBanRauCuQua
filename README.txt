1.Cài đặt database : 
https://drive.google.com/drive/folders/0B7LA3mLPbGcsSWozeTlONUI3ZEU?usp=sharing
(smallint = số tự tăng)
Thêm dữ liệu vào các bảng khác.
.
2. Cách sử dụng github trên netbeans : 
a) Down project
____Chọn menu Team -> git -> clone -> Repository URL là : https://github.com/khoaihdkg3/QuanLyBanHang.git
____Nhập user, password -> Next -> chọn Master -> Next -> finish -> Hộp thoại chọn Open project
b) Up project
____Chuột phải vào Project QuanLyBanHang -> Git 
____Chọn commit
____Chọn remote -> push để up lên Github.

3. Thông tin project
a) Model : các đối tượng lưu thông tin.
b) Interface : thiết kế hàm cho service
c) Service : tương tác dữ liệu trên Database, lưu dữ liệu vào các model tương ứng.
d) View : các giao diện
e) Resource : lưu tài nguyên khác như icon, image...
f) File main.java để run project

4. Mô hình hệ thống :

View <-> Service
.
View chỉ hiển thị dữ liệu, nhận thao tác của user qua giao diện và gọi Service.
Service thực hiện thao tác với dữ liệu, trả về dữ liệu yêu cầu.