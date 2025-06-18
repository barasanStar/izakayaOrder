package barasanStar.izakayaOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import barasanStar.izakayaOrder.config.StaffDetails;
import barasanStar.izakayaOrder.model.entity.Staff;
import barasanStar.izakayaOrder.repository.StaffRepository;

@Service
public class StaffDetailsService implements UserDetailsService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Staff staff = staffRepository.findByName(name)
				.orElseThrow(() -> new UsernameNotFoundException("該当スタッフが見つかりません"));

		//		return User.builder()
		//				.username(staff.getName())
		//				.password(staff.getPassword())
		//				.authorities(staff.getAccess().name())
		//				.disabled(!staff.isActive())
		//				.build();

		return new StaffDetails(staff);
	}
}
