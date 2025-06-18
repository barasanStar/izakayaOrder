package barasanStar.izakayaOrder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import barasanStar.izakayaOrder.enums.AccessRole;
import barasanStar.izakayaOrder.model.entity.Staff;
import barasanStar.izakayaOrder.repository.StaffRepository;
import barasanStar.izakayaOrder.util.DebugLogger;

@Component
public class InitialAdminLoader implements CommandLineRunner {
	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		if (staffRepository.count() == 0) {
			Staff admin = new Staff();
			admin.setName("admin");
			admin.setAccess(AccessRole.ADMIN);
			admin.setPassword(passwordEncoder.encode("admin123"));
			staffRepository.save(admin);
			DebugLogger.log(InitialAdminLoader.class, "■初期ADMINアカウントを作成しました。");

			Staff staff = new Staff();
			staff.setName("staff1");
			staff.setAccess(AccessRole.STAFF);
			staff.setPassword(passwordEncoder.encode("staff1"));
			staffRepository.save(staff);
			DebugLogger.log(InitialAdminLoader.class, "■staff1アカウントを作成しました。");
		}
	}

}
