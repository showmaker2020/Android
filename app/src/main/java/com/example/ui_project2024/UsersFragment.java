package com.example.ui_project2024;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ui_project2024.DB_Manager.Data_Stadium;

public class UsersFragment extends Fragment {

    Data_Stadium db;
    String email;

    public static UsersFragment newInstance(String email) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putString("email", email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        db = new Data_Stadium(getContext());
        if (getArguments() != null) {
            email = getArguments().getString("email");
        }
        TextView name = view.findViewById(R.id.edit_name_profile);
        name.setText(db.getName(email));
        TextView changeNameProfile = view.findViewById(R.id.chage_name_user);
        TextView change_email = view.findViewById(R.id.chage_email);
        TextView change_phone = view.findViewById(R.id.chage_phone);
        TextView Show_email = view.findViewById(R.id.show_email);
        TextView Show_phone = view.findViewById(R.id.show_phone);
        TextView Show_coin = view.findViewById(R.id.show_coin);
        TextView hide_email = view.findViewById(R.id.hide_email);
        TextView hide_phone = view.findViewById(R.id.hide_phone);
        TextView hide_coin = view.findViewById(R.id.hide_coin);
        Button change_pass = view.findViewById(R.id.change_password_button);
        // change name
        changeNameProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_password_verification, null);
                builder.setView(dialogView);

                // Thiết lập các thành phần trong dialog
                final EditText inputPassword = dialogView.findViewById(R.id.input_password);
                Button btnConfirm = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
                AlertDialog dialog = builder.create();
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = inputPassword.getText().toString();
                        if (db.checkPassword(email, password)) {
                                AlertDialog.Builder nameDialog = new AlertDialog.Builder(getContext());
                                nameDialog.setTitle("Nhập tên mới");
                                final EditText inputName = new EditText(getContext());
                                inputName.setHint("Tên mới");
                                inputName.setInputType(InputType.TYPE_CLASS_TEXT);
                                nameDialog.setView(inputName);
                            nameDialog.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String newName = inputName.getText().toString();
                                    name.setText(newName);  // Cập nhật tên trong TextView
                                    db.updateName(email, newName); // Cập nhật tên mới vào cơ sở dữ liệu
                                    Toast.makeText(getContext(), "Tên đã được cập nhật!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            nameDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            nameDialog.show();
                        } else {
                            inputPassword.setError("Mật khẩu không đúng, vui lòng thử lại.");
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        // change email
        change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_password_verification, null);
                builder.setView(dialogView);

                // Thiết lập các thành phần trong dialog
                final EditText inputPassword = dialogView.findViewById(R.id.input_password);
                Button btnConfirm_email = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel_email = dialogView.findViewById(R.id.btn_cancel);
                AlertDialog dialog = builder.create();
                btnConfirm_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = inputPassword.getText().toString();
                        if(db.checkPassword(email, password)){
                            AlertDialog.Builder emailDialog = new AlertDialog.Builder(getContext());
                            emailDialog.setTitle("Thay đổi email:");
                            LinearLayout layout = new LinearLayout(getContext());
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.setPadding(50, 20, 50, 10); // Padding để bố cục nhìn đẹp hơn

                            EditText inputEmailOld = new EditText(getContext());
                            inputEmailOld.setHint("Email hiện tại");
                            inputEmailOld.setInputType(InputType.TYPE_CLASS_TEXT);

                            EditText inputEmailNew = new EditText(getContext());
                            inputEmailNew.setHint("Email mới");
                            inputEmailNew.setInputType(InputType.TYPE_CLASS_TEXT);

                            layout.addView(inputEmailOld);
                            layout.addView(inputEmailNew);

                            emailDialog.setView(layout);
                            emailDialog.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String emailOld = inputEmailOld.getText().toString();
                                    String emailnew = inputEmailNew.getText().toString();
                                    if(db.checkEmail(emailOld)){
                                        db.updateEmail(emailOld, emailnew);
                                        Log.d("aaa", "onClick: update thanh cong");
                                        Log.d("aaa", "onClick: " + emailOld);
                                        Log.d("aaa", "onClick: " + emailnew);
                                        hide_email.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                        hide_email.setText(emailnew);
                                        hide_email.setVisibility(View.VISIBLE);
                                    } else {
                                        Log.d("aaa", "onClick: email khong hop le");
                                    }
                                }
                            });
                            emailDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            emailDialog.show();
                        } else {
                            inputPassword.setError("Mật khẩu không đúng, vui lòng thử lại.");
                        }
                    }
                });
                btnCancel_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        // change phone
        change_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_password_verification, null);
                builder.setView(dialogView);

                // Thiết lập các thành phần trong dialog
                final EditText inputPassword = dialogView.findViewById(R.id.input_password);
                Button btnConfirm_phone = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel_phone = dialogView.findViewById(R.id.btn_cancel);
                AlertDialog dialog = builder.create();
                btnConfirm_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = inputPassword.getText().toString();
                        if(db.checkPassword(email, password)){
                            AlertDialog.Builder phonelDialog = new AlertDialog.Builder(getContext());
                            phonelDialog.setTitle("Thay đổi số điện thoại:");
                            LinearLayout layout = new LinearLayout(getContext());
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.setPadding(50, 20, 50, 10); // Padding để bố cục nhìn đẹp hơn

                            EditText inputphoneOld = new EditText(getContext());
                            inputphoneOld.setHint("Sộ điện thoại hiện tại");
                            inputphoneOld.setInputType(InputType.TYPE_CLASS_TEXT);

                            EditText inputphoneNew = new EditText(getContext());
                            inputphoneNew.setHint("Sộ điện thoại mới");
                            inputphoneNew.setInputType(InputType.TYPE_CLASS_TEXT);

                            layout.addView(inputphoneOld);
                            layout.addView(inputphoneNew);

                            phonelDialog.setView(layout);
                            phonelDialog.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String phoneOld = inputphoneOld.getText().toString();
                                    String phoneNew = inputphoneNew.getText().toString();
                                    if(db.checkEmail(email)){
                                        db.updatePhone(email, phoneNew);
                                        Log.d("aaa", "onClick: update thanh cong");
                                        Log.d("aaa", "onClick: " + phoneOld);
                                        Log.d("aaa", "onClick: " + phoneNew);
                                        hide_phone.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                        hide_phone.setText(phoneNew);
                                        hide_phone.setVisibility(View.VISIBLE);
                                    } else {
                                        Log.d("aaa", "onClick: phone khong hop le");
                                    }
                                }

                            });
                            phonelDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            phonelDialog.show();
                        } else {
                            inputPassword.setError("Mật khẩu không đúng, vui lòng thử lại.");
                        }
                    }
                });
                btnCancel_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        // show email
        Show_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_password_verification, null);
                builder.setView(dialogView);

                // Thiết lập các thành phần trong dialog
                final EditText inputPassword = dialogView.findViewById(R.id.input_password);
                Button btnConfirm_show_emai = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel_show_email = dialogView.findViewById(R.id.btn_cancel);
                AlertDialog dialog = builder.create();
                btnConfirm_show_emai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = inputPassword.getText().toString();
                        if(db.checkPassword(email, password)){
                            hide_email.setInputType(InputType.TYPE_CLASS_TEXT);  // Đặt kiểu hiển thị text bình thường
                            hide_email.setText(email);  // Lấy email từ database
                            hide_email.setVisibility(View.VISIBLE);
                        }
                        else {
                            inputPassword.setError("Mật khẩu không đúng, vui lòng thử lại.");
                        }
                    }
                });
                btnCancel_show_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        // show phone
        Show_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_password_verification, null);
                builder.setView(dialogView);

                // Thiết lập các thành phần trong dialog
                final EditText inputPassword = dialogView.findViewById(R.id.input_password);
                Button btnConfirm_show_phone = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel_show_phone = dialogView.findViewById(R.id.btn_cancel);
                AlertDialog dialog = builder.create();
                btnConfirm_show_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = inputPassword.getText().toString();
                        if(db.checkPassword(email, password)){
                            String phone = db.getPhone(email);
                            hide_phone.setInputType(InputType.TYPE_CLASS_TEXT);
                            hide_phone.setText(phone);  // Lấy phone từ database
                            hide_phone.setVisibility(View.VISIBLE);
                        }
                        else {
                            inputPassword.setError("Mật khẩu không đúng, vui lòng thử lại.");
                        }
                    }
                });
                btnCancel_show_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        // show coin
        Show_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_password_verification, null);
                builder.setView(dialogView);

                // Thiết lập các thành phần trong dialog
                final EditText inputPassword = dialogView.findViewById(R.id.input_password);
                Button btnConfirm_show_coin = dialogView.findViewById(R.id.btn_confirm);
                Button btnCancel_show_coin = dialogView.findViewById(R.id.btn_cancel);
                AlertDialog dialog = builder.create();

                btnConfirm_show_coin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = inputPassword.getText().toString();
                        if(db.checkPassword(email, password)){
                            int coin = db.gettotal();
                            hide_coin.setInputType(InputType.TYPE_CLASS_TEXT);
                            hide_coin.setText(String.valueOf(coin)); // Lấy coin từ database
                            hide_coin.setVisibility(View.VISIBLE);
                        }
                        else {
                            inputPassword.setError("Mật khẩu không đúng, vui lòng thử lại.");
                        }
                    }
                });
                btnCancel_show_coin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        // change password
        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder passchange = new AlertDialog.Builder(getContext());
                passchange.setTitle("Thay đổi mật khẩu:");
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(50, 20, 50, 10); // Padding để bố cục nhìn đẹp hơn
                EditText passold = new EditText(getContext());
                Drawable visibilityIcon = resizeDrawable(R.drawable.show, 40, 40);
                passold.setHint("Mật khẩu hiện tại");
                passold.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                EditText passnew = new EditText(getContext());
                passnew.setHint("Mật khẩu mới");
                passnew.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                EditText passnew_comfirm = new EditText(getContext());
                passnew_comfirm.setHint("Xác thực mật khẩu");
                passnew_comfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                layout.addView(passold);
                layout.addView(passnew);
                layout.addView(passnew_comfirm);
                passchange.setView(layout);
                passchange.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String pass_old = passold.getText().toString();
                        String pass_new = passnew.getText().toString();
                        String pass_new_comfirm = passnew_comfirm.getText().toString();
                        if(db.checkPassword(email, pass_old)){
                            Log.d("aaa", "onClick: update thanh cong");
                            Log.d("aaa", "onClick: " + pass_old);
                            Log.d("aaa", "onClick: " + pass_new);
                            Log.d("aaa", "onClick: " + pass_new_comfirm);
                            if(pass_new.equals(pass_new_comfirm)){
                                db.updatePassword(email, pass_new);
                                Log.d("aaa", "onClick: update thanh cong");
                            } else {
                                Log.d("aaa", "onClick: update khong thanh cong");
                            }
                        } else {
                            Log.d("aaa", "onClick: pass khong hop le");
                        }
                    }
                });
                passchange.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                passchange.show();
            }
        });
        return view;
    }

//    public void init(Button btnConfirm, Button btnCancel, EditText inputPassword){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_password_verification, null);
//        builder.setView(dialogView);
//
//        // Thiết lập các thành phần trong dialog
//         inputPassword = dialogView.findViewById(R.id.input_password);
//         btnConfirm = dialogView.findViewById(R.id.btn_confirm);
//         btnCancel = dialogView.findViewById(R.id.btn_cancel);
//        AlertDialog dialog = builder.create();
//    }

    private Drawable resizeDrawable(int drawableId, int width, int height) {
        Drawable drawable = getResources().getDrawable(drawableId, null);
        drawable.setBounds(0, 0, width, height);
        return drawable;
    }

}
