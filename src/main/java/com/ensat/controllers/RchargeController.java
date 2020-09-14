package com.ensat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensat.entities.Voucher;
import com.ensat.services.VoucherService;

/**
 * voucher controller.
 */
@Controller
public class RchargeController {

	private VoucherService voucherService;
	
	
	

    

	public void setVoucherService(VoucherService voucherService) {
		this.voucherService = voucherService;
	}

	/**
     * List all voucher.
     *
     * @param model
     * @return
     */
    
	@RequestMapping("product/recharge")
    public String sellProduct(Model model) {
        model.addAttribute("voucher", new Voucher());
        return "rechargeVoucher";
    }

	@RequestMapping(value = "voucher", method = RequestMethod.POST)
    public String saveVoucher(Voucher voucher) {
		voucherService.saveVoucher(voucher);
        return "redirect:/voucher/" + voucher.getId();
    }
   
}
