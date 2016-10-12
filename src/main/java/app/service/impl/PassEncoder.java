package app.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("passEncoder")
public class PassEncoder extends BCryptPasswordEncoder {
}
