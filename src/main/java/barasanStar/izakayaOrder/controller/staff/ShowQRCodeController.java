package barasanStar.izakayaOrder.controller.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import barasanStar.izakayaOrder.service.QRCodeService;

@Controller
@RequestMapping("/staff")
public class ShowQRCodeController {
	@Autowired
	private QRCodeService qrCodeService;

	@GetMapping("show-QR-code")
	public String showQRCode(Model model) throws Exception {
		String accessURL = (String) model.getAttribute("accessURL");
		String qrCode = qrCodeService.makeQRCode(accessURL, 300, 300);
		model.addAttribute("qrCode", qrCode);
		return "staff/show-QR-code";
	}
}
