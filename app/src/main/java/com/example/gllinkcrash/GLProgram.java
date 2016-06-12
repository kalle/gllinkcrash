package com.example.gllinkcrash;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by kalle on 2016-06-10.
 */
public class GLProgram {

    private static final String TAG = "GLProgram";

    private GLProgram() {}

    public static void compile() {
        new GLProgram().compileProgram();
    }

    private String getVertexShader() {
        return "attribute vec4 a_Position;\n" +
                "attribute vec4 a_TexCoordinate;\n" +
                "varying vec2 v_TexCoordinate;\n" +
                "void main()\n" +
                "{\n" +
                "    gl_Position = a_Position;\n" +
                "    v_TexCoordinate = a_TexCoordinate.xy;\n" +
                "}";
    }

    private String getFragmentShader() {
        return "#version 100\n" +
                "precision mediump float;\n" +
                "uniform sampler2D u_Texture[2];\n" +
                "uniform highp vec2 u_set1;\n" +
                "uniform highp vec2 u_set2;\n" +
                "uniform bool save_intermediate;\n" +
                "uniform highp vec2 img_size;\n" +
                "void main ()\n" +
                "{\n" +
                "  highp int lb_1;\n" +
                "  highp vec2 lc_2;\n" +
                "  lowp vec2 ld_3;\n" +
                "  highp float le_4;\n" +
                "  highp vec2 lf_5;\n" +
                "  lowp vec2 lg_6;\n" +
                "  highp float lh_7;\n" +
                "  highp vec2 li_8;\n" +
                "  lowp vec2 lj_9;\n" +
                "  highp float lk_10;\n" +
                "  highp vec2 ll_11;\n" +
                "  highp vec2 lm_12;\n" +
                "  lm_12 = gl_FragCoord.xy;\n" +
                "  highp vec2 ln_13;\n" +
                "  ln_13.x = u_set1.x;\n" +
                "  ln_13.y = u_set2.x;\n" +
                "  ll_11 = ((2.0 * ln_13) * ln_13);\n" +
                "  highp float lo_14;\n" +
                "  if ((u_set1.x > u_set2.x)) {\n" +
                "    lo_14 = ln_13.x;\n" +
                "  } else {\n" +
                "    lo_14 = ln_13.y;\n" +
                "  };\n" +
                "  lk_10 = (2.0 * lo_14);\n" +
                "  highp vec2 lp_15;\n" +
                "  lp_15 = (gl_FragCoord.xy / img_size);\n" +
                "  lj_9 = ((texture2D (u_Texture[0], lp_15) * 2.0) - 1.0).xy;\n" +
                "  li_8 = vec2(1.0, 1.0);\n" +
                "  lowp vec2 lq_16;\n" +
                "  lq_16 = ((texture2D (u_Texture[1], lp_15).xy * 2.0) - 1.0);\n" +
                "  lc_2 = lm_12;\n" +
                "  lf_5 = lm_12;\n" +
                "  lg_6 = lq_16;\n" +
                "  ld_3 = -(lq_16);\n" +
                "  le_4 = 0.0;\n" +
                "  lh_7 = 0.0;\n" +
                "  lb_1 = 0;\n" +
                "  while (true) {\n" +
                "    if (!(((lh_7 < lk_10) && (lb_1 < 100)))) {\n" +
                "      break;\n" +
                "    };\n" +
                "    highp vec2 lr_17;\n" +
                "    lowp vec2 ls_18;\n" +
                "    highp float lt_19;\n" +
                "    lr_17 = lf_5;\n" +
                "    ls_18 = lg_6;\n" +
                "    lt_19 = lh_7;\n" +
                "    lowp vec2 lu_20;\n" +
                "    highp vec2 lv_21;\n" +
                "    lv_21 = (lf_5 / img_size);\n" +
                "    lowp vec2 lw_22;\n" +
                "    lw_22 = ((texture2D (u_Texture[1], lv_21).xy * 2.0) - 1.0);\n" +
                "    lu_20 = lw_22;\n" +
                "    lowp float lx_23;\n" +
                "    lx_23 = sqrt(dot (lw_22, lw_22));\n" +
                "    if ((lx_23 > 0.1)) {\n" +
                "      lowp float ly_24;\n" +
                "      ly_24 = dot (lw_22, lg_6);\n" +
                "      if ((ly_24 < 0.0)) {\n" +
                "        lu_20 = -(lw_22);\n" +
                "      };\n" +
                "      ls_18 = lu_20;\n" +
                "    } else {\n" +
                "      lu_20 = ls_18;\n" +
                "    };\n" +
                "    lowp float lz_25;\n" +
                "    lz_25 = abs(lu_20.x);\n" +
                "    lowp float lab_26;\n" +
                "    lab_26 = abs(lu_20.y);\n" +
                "    highp float lbb_27;\n" +
                "    if ((lz_25 > lab_26)) {\n" +
                "      lowp float lcb_28;\n" +
                "      lcb_28 = sign(lu_20.x);\n" +
                "      lbb_27 = abs(((\n" +
                "        (fract(lf_5.x) - 0.5)\n" +
                "       - lcb_28) / lu_20.x));\n" +
                "    } else {\n" +
                "      lowp float ldb_29;\n" +
                "      ldb_29 = sign(lu_20.y);\n" +
                "      lbb_27 = abs(((\n" +
                "        (fract(lf_5.y) - 0.5)\n" +
                "       - ldb_29) / lu_20.y));\n" +
                "    };\n" +
                "    lr_17 = (lf_5 + (lbb_27 * lu_20));\n" +
                "    lt_19 = (lh_7 + lbb_27);\n" +
                "    lf_5 = lr_17;\n" +
                "    lg_6 = ls_18;\n" +
                "    lh_7 = lt_19;\n" +
                "    highp vec2 leb_30;\n" +
                "    leb_30 = (lbb_27 * exp((\n" +
                "      (-(lt_19) * lt_19)\n" +
                "     / ll_11)));\n" +
                "    highp vec2 lfb_31;\n" +
                "    lfb_31 = (lr_17 / img_size);\n" +
                "    lj_9 = (lj_9 + (leb_30 * (\n" +
                "      (texture2D (u_Texture[0], lfb_31) * 2.0)\n" +
                "     - 1.0).xy));\n" +
                "    li_8 = (li_8 + leb_30);\n" +
                "    lb_1++;\n" +
                "  };\n" +
                "  lb_1 = 0;\n" +
                "  while (true) {\n" +
                "    if (!(((le_4 < lk_10) && (lb_1 < 100)))) {\n" +
                "      break;\n" +
                "    };\n" +
                "    highp vec2 lgb_32;\n" +
                "    lowp vec2 lhb_33;\n" +
                "    highp float lib_34;\n" +
                "    lgb_32 = lc_2;\n" +
                "    lhb_33 = ld_3;\n" +
                "    lib_34 = le_4;\n" +
                "    lowp vec2 ljb_35;\n" +
                "    highp vec2 lkb_36;\n" +
                "    lkb_36 = (lc_2 / img_size);\n" +
                "    lowp vec2 llb_37;\n" +
                "    llb_37 = ((texture2D (u_Texture[1], lkb_36).xy * 2.0) - 1.0);\n" +
                "    ljb_35 = llb_37;\n" +
                "    lowp float lmb_38;\n" +
                "    lmb_38 = sqrt(dot (llb_37, llb_37));\n" +
                "    if ((lmb_38 > 0.1)) {\n" +
                "      lowp float lnb_39;\n" +
                "      lnb_39 = dot (llb_37, ld_3);\n" +
                "      if ((lnb_39 < 0.0)) {\n" +
                "        ljb_35 = -(llb_37);\n" +
                "      };\n" +
                "      lhb_33 = ljb_35;\n" +
                "    } else {\n" +
                "      ljb_35 = lhb_33;\n" +
                "    };\n" +
                "    lowp float lob_40;\n" +
                "    lob_40 = abs(ljb_35.x);\n" +
                "    lowp float lpb_41;\n" +
                "    lpb_41 = abs(ljb_35.y);\n" +
                "    highp float lqb_42;\n" +
                "    if ((lob_40 > lpb_41)) {\n" +
                "      lowp float lrb_43;\n" +
                "      lrb_43 = sign(ljb_35.x);\n" +
                "      lqb_42 = abs(((\n" +
                "        (fract(lc_2.x) - 0.5)\n" +
                "       - lrb_43) / ljb_35.x));\n" +
                "    } else {\n" +
                "      lowp float lsb_44;\n" +
                "      lsb_44 = sign(ljb_35.y);\n" +
                "      lqb_42 = abs(((\n" +
                "        (fract(lc_2.y) - 0.5)\n" +
                "       - lsb_44) / ljb_35.y));\n" +
                "    };\n" +
                "    lgb_32 = (lc_2 + (lqb_42 * ljb_35));\n" +
                "    lib_34 = (le_4 + lqb_42);\n" +
                "    lc_2 = lgb_32;\n" +
                "    ld_3 = lhb_33;\n" +
                "    le_4 = lib_34;\n" +
                "    highp vec2 ltb_45;\n" +
                "    ltb_45 = (lqb_42 * exp((\n" +
                "      (-(lib_34) * lib_34)\n" +
                "     / ll_11)));\n" +
                "    highp vec2 lub_46;\n" +
                "    lub_46 = (lgb_32 / img_size);\n" +
                "    lj_9 = (lj_9 + (ltb_45 * (\n" +
                "      (texture2D (u_Texture[0], lub_46) * 2.0)\n" +
                "     - 1.0).xy));\n" +
                "    li_8 = (li_8 + ltb_45);\n" +
                "    lb_1++;\n" +
                "  };\n" +
                "  lj_9 = (lj_9 / li_8);\n" +
                "  if (save_intermediate) {\n" +
                "    mediump vec4 lvb_47;\n" +
                "    lowp vec4 lwb_48;\n" +
                "    lowp vec4 lxb_49;\n" +
                "    lxb_49.yw = vec2(0.0, 0.0);\n" +
                "    lxb_49.x = (log((1.0 + \n" +
                "      (255.0 * abs(lj_9.x))\n" +
                "    )) / 5.545177);\n" +
                "    lxb_49.z = (log((1.0 + \n" +
                "      (255.0 * abs(lj_9.y))\n" +
                "    )) / 5.545177);\n" +
                "    lwb_48 = lxb_49;\n" +
                "    if ((lj_9.x < 0.0)) {\n" +
                "      lwb_48.xy = lxb_49.yx;\n" +
                "    };\n" +
                "    if ((lj_9.y < 0.0)) {\n" +
                "      lwb_48.zw = lwb_48.wz;\n" +
                "    };\n" +
                "    lvb_47 = lwb_48;\n" +
                "    gl_FragColor = lvb_47;\n" +
                "  } else {\n" +
                "    lowp float lyb_50;\n" +
                "    lowp float lzb_51;\n" +
                "    lzb_51 = clamp (((lj_9.x - -0.2) / 0.4), 0.0, 1.0);\n" +
                "    lyb_50 = (lzb_51 * (lzb_51 * (3.0 - \n" +
                "      (2.0 * lzb_51)\n" +
                "    )));\n" +
                "    mediump float lac_52;\n" +
                "    if ((lyb_50 < u_set1.y)) {\n" +
                "      lac_52 = 0.0;\n" +
                "    } else {\n" +
                "      lac_52 = 1.0;\n" +
                "    };\n" +
                "    lowp float lbc_53;\n" +
                "    lowp float lcc_54;\n" +
                "    lcc_54 = clamp (((lj_9.y - -0.2) / 0.4), 0.0, 1.0);\n" +
                "    lbc_53 = (lcc_54 * (lcc_54 * (3.0 - \n" +
                "      (2.0 * lcc_54)\n" +
                "    )));\n" +
                "    mediump float ldc_55;\n" +
                "    if ((lbc_53 < u_set2.y)) {\n" +
                "      ldc_55 = 0.0;\n" +
                "    } else {\n" +
                "      ldc_55 = 1.0;\n" +
                "    };\n" +
                "    mediump vec4 lec_56;\n" +
                "    lec_56.zw = vec2(1.0, 1.0);\n" +
                "    lec_56.x = lac_52;\n" +
                "    lec_56.y = ldc_55;\n" +
                "    gl_FragColor = lec_56;\n" +
                "  };\n" +
                "}\n" +
                "\n";
    }

    protected void compileProgram() {
        int program = GLES20.glCreateProgram();
        checkGlError("glCreateProgram");

        int vshader = compileShader(getVertexShader(), GLES20.GL_VERTEX_SHADER);
        int fshader = compileShader(getFragmentShader(), GLES20.GL_FRAGMENT_SHADER);

        GLES20.glAttachShader(program, vshader);
        checkGlError("glAttachShader (vertex)");
        GLES20.glAttachShader(program, fshader);
        checkGlError("glAttachShader (fragment)");

        Log.i(TAG, "Before glLinkProgram");
        GLES20.glLinkProgram(program);
        Log.i(TAG, "After glLinkProgram");
        checkGlError("glLinkProgram");
    }

    private int compileShader(String shaderSource, int type) {
        int shader = GLES20.glCreateShader(type);
        checkGlError("glCreateShader (" + type + ")");

        GLES20.glShaderSource(shader, shaderSource);
        checkGlError("glShaderSource (" + type + ")");
        GLES20.glCompileShader(shader);
        checkGlError("glCompileShader");

        return shader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}
