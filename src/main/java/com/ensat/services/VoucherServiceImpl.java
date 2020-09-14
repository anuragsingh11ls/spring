package com.ensat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.Voucher;
import com.ensat.repositories.VoucherRepository;



/**
 * Product service implement.
 */
@Service
public class VoucherServiceImpl implements VoucherService {

    private VoucherRepository voucherRepository;

    @Autowired
    public void setProductRepository(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

	@Override
	public Iterable<Voucher> listAllVouchers() {
		// TODO Auto-generated method stub
		return voucherRepository.findAll(); 
	}

	@Override
	public Voucher getVoucherById(Integer id) {
		// TODO Auto-generated method stub
		return voucherRepository.findOne(id);
	}

	@Override
	public Voucher saveVoucher(Voucher voucher) {
		// TODO Auto-generated method stub
		return  voucherRepository.save(voucher);
	}

	@Override
	public void deleteProduct(Integer id) {
		voucherRepository.delete(id);
		
	}
    
    

	/*
	 * @Override public Iterable<Voucher> listAllProducts() { return
	 * voucherRepository.findAll(); }
	 * 
	 * @Override public Product getProductById(Integer id) { return
	 * voucherRepository.findOne(id); }
	 * 
	 * @Override public Voucher saveProduct(Voucher product) { return
	 * voucherRepository.save(product); }
	 * 
	 * @Override public void deleteProduct(Integer id) {
	 * voucherRepository.delete(id); }
	 */

}
