package zaietsv.complextask.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import zaietsv.complextask.mvc.instance.User;

public class UserDAO extends AbstractDAO<User> {
	
	/**
	 * Constructs an empty data access object for `user` table
	 */
	public UserDAO() {
		super();
	}

	/**
	 * Constructs a data access object for `user` table using connection parameter
	 * @param connection - an instance of Connection class
	 */
	public UserDAO(Connection connection) {
		super(connection);
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.dao.DataAccessObject#insert(zaietsv.complextask.mvc.instance.Instance)
	 */
	@Override
	public long insert(User user) {
		long id = -1;
		String sql = "INSERT INTO user (login, password, email, reg_date) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setDate(4, user.getReg_date());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.dao.DataAccessObject#read(long)
	 */
	@Override
	public User read(long id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		User user = new User();
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					((User)user).setId(rs.getLong("id"));
					((User)user).setLogin(rs.getString("login"));
					((User)user).setPassword(rs.getString("password"));
					((User)user).setEmail(rs.getString("email"));
					((User)user).setReg_date(rs.getDate("reg_date"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.dao.DataAccessObject#update(zaietsv.complextask.mvc.instance.Instance)
	 */
	@Override
	public int update(User user) {
		String sql = "UPDATE user SET `login` = ?, `password` = ?, `email` = ?  WHERE `id` = ?";
		int rows = 0;
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setLong(4, user.getId());
			try {
				rows = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.dao.DataAccessObject#delete(long)
	 */
	@Override
	public boolean delete(long id) {
		String sql = " DELETE FROM `user` WHERE id = ? ";
		boolean res = false;
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setLong(1, id);
			res = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.dao.DataAccessObject#readAll()
	 */
	@Override
	public ArrayList<User> readAll() {
		String sql = "SELECT * FROM user WHERE 1";
		ArrayList<User> users = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("id"));
					user.setLogin(rs.getString("login"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setReg_date(rs.getDate("reg_date"));
					users.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
