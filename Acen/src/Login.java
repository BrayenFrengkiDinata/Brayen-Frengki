import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Login {

	private JFrame frmMyLoginSystem;
	private JTextField txtUser;
	private JTable table;
	private DefaultTableModel model;
	private JButton login;
	private JButton edit;
	private JButton hapus;
	private JPasswordField txtPass;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	int row;
	
	
	ArrayList<Akun> akun = new ArrayList<Akun>();
	
	
	public void refreshData() {
		model.setRowCount(0);
		for (int i=0; i<akun.size(); i++) {
			Object [] obj = {akun.get(i).user, akun.get(i).pass};
			model.addRow(obj);
		}
	}

	/**
	 * Main method untuk jalan apk
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmMyLoginSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Aplikasinya
	 */
	public Login() {
		initialize();
	}

	/**
	 * Frame GUI dan Fungsi-fungsi
	 */
	private void initialize() {
		frmMyLoginSystem = new JFrame();
		
		frmMyLoginSystem.getContentPane().setBackground(new Color(232, 67, 147));
		frmMyLoginSystem.setType(Type.UTILITY);
		frmMyLoginSystem.setTitle("My Login System");
		frmMyLoginSystem.setBounds(100, 100, 449, 445);
		frmMyLoginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyLoginSystem.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN SISTEM");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(122, 11, 186, 53);
		frmMyLoginSystem.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(77, 116, 88, 23);
		frmMyLoginSystem.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(77, 163, 88, 23);
		frmMyLoginSystem.getContentPane().add(lblNewLabel_1_1);
		
		txtUser = new JTextField();
		txtUser.setBounds(176, 117, 161, 23);
		frmMyLoginSystem.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 266, 413, 129);
		frmMyLoginSystem.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Password"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		//fungsi klik tabel
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login.setEnabled(false);
				edit.setEnabled(true);
				hapus.setEnabled(true);
				
				int row = table.rowAtPoint(e.getPoint());
				
				txtUser.setText(table.getValueAt(row, 0).toString());
				txtPass.setText(table.getValueAt(row, 1).toString());
			}
		});
		login = new JButton("Login");
		login.setFocusable(false);
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				login.setBackground(Color.BLACK);
				login.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				login.setBackground(new Color(255, 255, 255));
				login.setForeground(new Color(0, 0, 0));
			}
		});
		login.setBackground(new Color(255, 255, 255));
		login.setForeground(new Color(0, 0, 0));
		
		//fungsi login, menyimpan data user dan pass
		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				akun.add(new Akun(txtUser.getText(),txtPass.getText()));
				model = (DefaultTableModel)table.getModel();
				int option = JOptionPane.showConfirmDialog(txtPass, "Lanjut Simpan User dan Pass?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

			    if (option == 0) {
			    	if (txtUser.getText().equals("")) {
						JOptionPane.showMessageDialog(txtPass, "Username tidak boleh kosong yaaaaa......");
					} else if (txtPass.getText().equals("")) {
						JOptionPane.showMessageDialog(txtPass, "Password juga tidak boleh kosong sayang....");
					} else {
						refreshData();
						txtUser.setText("");
						txtPass.setText("");
					}
			    }
			}
		});
		login.setFont(new Font("Tahoma", Font.BOLD, 11));
		login.setBounds(248, 199, 89, 23);
		frmMyLoginSystem.getContentPane().add(login);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(175, 163, 161, 22);
		frmMyLoginSystem.getContentPane().add(txtPass);
		
		edit = new JButton("Edit");
		edit.setEnabled(false);
		
		//fungsi edit data
		edit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (txtUser.getText().equals("")) {
					JOptionPane.showMessageDialog(edit, "Username tidak boleh kosong!");
				} else if (txtPass.getText().equals("")) {
					JOptionPane.showMessageDialog(edit, "Password tidak boleh kosong!");
				} else {
					akun.get(row).user=txtUser.getText();
					akun.get(row).pass=txtPass.getText();
					
					JOptionPane.showMessageDialog(edit, "Data di update");
					
					refreshData();
					
					txtUser.setText("");
					txtPass.setText("");
					
					login.setEnabled(true);
					edit.setEnabled(false);
					hapus.setEnabled(false);
				}
			}
		});
		edit.setFocusable(false);
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				edit.setBackground(Color.BLACK);
				edit.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				edit.setBackground(new Color(255, 255, 255));
				edit.setForeground(new Color(0, 0, 0));
			}
		});
		edit.setBackground(new Color(255, 255, 255));
		edit.setForeground(new Color(0, 0, 0));
		edit.setFont(new Font("Tahoma", Font.BOLD, 10));
		edit.setBounds(10, 235, 65, 23);
		frmMyLoginSystem.getContentPane().add(edit);
		
		hapus = new JButton("Hapus");
		hapus.setEnabled(false);
		
		//fungsi hapus data dari tabel dan arraylist
		hapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(hapus, "Hapus Data","Delete this row?",JOptionPane.YES_NO_OPTION);
				int row=table.getSelectedRow();
			 	if (row>=0 && confirm == 0) {
			 		
			 		model.removeRow(row);
			 		akun.remove(row);
			 		
			 		JOptionPane.showMessageDialog(hapus, "Data Sudah Terhapus");
			 		
			 		txtUser.setText("");
					txtPass.setText("");
					
					login.setEnabled(true);
					
					edit.setEnabled(false);
					hapus.setEnabled(false);
			 	} else {
			 		JOptionPane.showMessageDialog(hapus, "Pilih baris yang akan dihapus");
			 	}
			}
		});
		hapus.setFocusable(false);
		hapus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hapus.setBackground(Color.BLACK);
				hapus.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hapus.setBackground(new Color(255, 255, 255));
				hapus.setForeground(new Color(0, 0, 0));
			}
		});
		hapus.setBackground(new Color(255, 255, 255));
		hapus.setForeground(new Color(0, 0, 0));
		hapus.setFont(new Font("Tahoma", Font.BOLD, 10));
		hapus.setBounds(85, 235, 65, 23);
		frmMyLoginSystem.getContentPane().add(hapus);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(Color.BLACK);
				btnNewButton.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(new Color(255, 255, 255));
				btnNewButton.setForeground(new Color(0, 0, 0));
			}
		});
		btnNewButton.setFocusable(false);
		
		//fungsi tombol reset
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setEnabled(true);
				edit.setEnabled(false);
				hapus.setEnabled(false);
				txtUser.setText("");
				txtPass.setText("");
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(272, 87, 65, 19);
		frmMyLoginSystem.getContentPane().add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("Ini data login pak!");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(lblNewLabel_2, "Saya sudah hilang ide pak, sekian");
			}
		});
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(160, 234, 131, 23);
		frmMyLoginSystem.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/acen/fhfah.jpg")));
		lblNewLabel_3.setBounds(0, 0, 433, 406);
		frmMyLoginSystem.getContentPane().add(lblNewLabel_3);
	}
}
