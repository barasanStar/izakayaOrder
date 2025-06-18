package barasanStar.izakayaOrder.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import barasanStar.izakayaOrder.model.entity.Staff;

public class StaffDetails implements UserDetails {

	private final Staff staff;

	public StaffDetails(Staff staff) {
		this.staff = staff;
	}

	public String getName() {
		return staff.getName();
	}

	public Long getId() {
		return staff.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(staff.getAccess().toString()));
	}

	@Override
	public String getPassword() {
		return staff.getPassword();
	}

	@Override
	public String getUsername() {
		return staff.getName();
	}

	public String getDisplayName() {
		return staff.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}