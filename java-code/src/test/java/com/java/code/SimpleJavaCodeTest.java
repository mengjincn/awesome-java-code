package com.java.code;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.java.code.util.MathUtils;

/**
 * 该类用于测试一些简单地Java代码（不需要Spring上下文）
 * 
 * @author 孟进
 *
 */
public class SimpleJavaCodeTest {
	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void testHelloWorld() {
		System.out.println("Hello World!");
		assertThat(capture.toString(), containsString("World"));
	}

	@Test
	public void testIsPrime() {
		assertTrue(MathUtils.isPrime(5));
	}

	@Test
	public void testSqrt() {
		assertThat(MathUtils.sqrt(2), equalTo(1.414213562373095));
	}

	@Test
	public void testBaseEncoding() {
		BaseEncoding baseEncoding = BaseEncoding.base64();
		String hello = "Hello World";
		String encodeHello = baseEncoding.encode(hello.getBytes(Charsets.UTF_8));
		assertThat(encodeHello, equalTo("SGVsbG8gV29ybGQ="));
		String decodeHello = new String(baseEncoding.decode(encodeHello), Charsets.UTF_8);
		assertThat(decodeHello, equalTo(hello));
	}

	@Test
	public void testHashingFunction() {
		byte[] b = Hashing.md5().newHasher().putString("Hello World", Charsets.UTF_8).hash().asBytes();
		assertThat(BaseEncoding.base16().encode(b), equalTo("B10A8DB164E0754105B7A99BE72E3FE5"));
	}

	@Test
	public void testDigestUtils() {
		String s = "Hello World";
		assertThat(DigestUtils.md5Hex(s), equalTo("b10a8db164e0754105b7a99be72e3fe5"));
		assertThat(DigestUtils.sha256Hex(s),
				equalTo("a591a6d40bf420404a011733cfb7b190d62c65bf0bcda32b57b277d9ad9f146e"));
		assertThat(DigestUtils.shaHex(s), equalTo("0a4d55a8d778e5022fab701977c5d840bbc486d0"));
		assertThat(Base64.encodeBase64String(s.getBytes(Charsets.UTF_8)), equalTo("SGVsbG8gV29ybGQ="));
		assertThat(org.apache.commons.codec.binary.StringUtils
				.newStringUtf8(Base64.decodeBase64("SGVsbG8gV29ybGQ=".getBytes(Charsets.UTF_8))), equalTo(s));
	}

	@Test
	public void testJsoup() throws IOException {
		String cookies = "PSTM=1500457700; BIDUPSID=2DE8A70877AA2E9F5043D32C5FEEA923; FP_UID=5d2ecc2d50b61755ad511b972eaad63e; BD_HOME=1; H_PS_PSSID=1456_21087_17001; BD_UPN=12314353";
		Map<String, String> cookiesMap = Splitter.on(";").omitEmptyStrings().trimResults().withKeyValueSeparator("=")
				.split(cookies);
		cookiesMap = Maps.newHashMap(cookiesMap);
		cookiesMap.put("BAIDUID", "A337395A66868DBB15B9DC02A368AA2E:FG=1");
		Document doc = Jsoup.connect("https://www.baidu.com").cookies(cookiesMap)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36")
				.get();
		System.out.println(doc);
	}

	public static void main(String[] args) {
		while (true) {
			try {
				Thread.sleep(3600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
