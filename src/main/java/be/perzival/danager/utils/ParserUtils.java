package be.perzival.danager.utils;

/**
 * Created by Perzival on 05/08/2017.
 */
public class ParserUtils {

    public static final String concatArgWithSpacer(String[] args, String spacer) {
        return concatArgWithSpacer(0, args, spacer);
    }

    public static final String concatArgWithSpacer(int offset, String[] args, String spacer) {
        StringBuilder concat = new StringBuilder();
        for(int i = offset; i < args.length; ++i) {
            concat.append(args[i]+ spacer);
        }
        return concat.toString();
    }

    public static final String concatArg(String[] args) {
        return concatArgWithSpacer(args,"");
    }

    public static final String concatArg(int offset, String[] args) {
        return concatArgWithSpacer(offset, args,"");
    }


}
